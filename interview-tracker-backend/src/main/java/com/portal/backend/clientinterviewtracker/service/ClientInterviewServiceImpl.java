package com.portal.backend.clientinterviewtracker.service;

import com.portal.backend.clientinterviewtracker.dao.ClientDetailRepository;
import com.portal.backend.clientinterviewtracker.dao.ClientInterviewRepository;
import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsDto;
import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsSearchRequestDto;
import com.portal.backend.clientinterviewtracker.entity.ClientDetails;
import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
import com.portal.backend.clientinterviewtracker.enums.SortType;
import com.portal.backend.clientinterviewtracker.exception.DetailsNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ClientInterviewServiceImpl implements ClientInterviewService{
    private static final String DEFAULT_SORTING_FIELD = "interviewDate";
    private static final int DEFAULT_PAGE_SIZE = 200;

    @Autowired
    ClientInterviewRepository clientInterviewRepository;

    @Autowired
    ClientDetailRepository clientDetailRepository;

    @Override
    public Page<InterviewDetailsDto> getAllInterviewDetails(Pageable pageable) {

        Page<InterviewDetails> interviewDetailsPageList = clientInterviewRepository.findAllByRowEndDateIsNullOrderByCreatedDateDesc(pageable);

        if(interviewDetailsPageList.isEmpty() || interviewDetailsPageList.getSize() == 0)
            throw new EntityNotFoundException("Interview Details not present");

        List<InterviewDetailsDto> interviewDetailsDtoList = interviewDetailsPageList.stream().map(s -> new InterviewDetailsDto
                (s.getId(), s.getClientDetails().getClientName(), s.getProjectCode(), s.getInterviewType(), s.getAssociateName(), s.getKbPageLink(), s.getComments(), s.isUpdatedKbPage(), s.isRetired(), s.getInterviewDate())).toList();

        int totalRecords = clientInterviewRepository.findAllByRowEndDateIsNullOrderByCreatedDateDesc().size();
        return new PageImpl<>(interviewDetailsDtoList, interviewDetailsPageList.getPageable(),totalRecords);

    }

    @Override
    @Transactional
    public InterviewDetailsDto updateInterview(Long id, InterviewDetailsDto interviewDetailsDto) {

                Optional<InterviewDetails> optionalInterviewDetails = clientInterviewRepository.findById(id);
                if (optionalInterviewDetails.isPresent()) {
                    InterviewDetails existingInterviewDetails = optionalInterviewDetails.get();
                    existingInterviewDetails.setInterviewType(interviewDetailsDto.getInterviewType());
                    existingInterviewDetails.setAssociateName(interviewDetailsDto.getAssociateName());
                    existingInterviewDetails.setKbPageLink(interviewDetailsDto.getKbPageLink());
                    existingInterviewDetails.setComments(interviewDetailsDto.getComments());
                    existingInterviewDetails.setRetired(interviewDetailsDto.isRetired());
                    existingInterviewDetails.setUpdatedKbPage(interviewDetailsDto.isUpdatedKbPage());
                    existingInterviewDetails.setInterviewDate(interviewDetailsDto.getInterviewDate());
                    existingInterviewDetails.setRowUpdtDate(new Date());
                    existingInterviewDetails.setProjectCode(interviewDetailsDto.getProjectCode());

                    Optional<ClientDetails> optionalClientDetails = clientDetailRepository.findById(existingInterviewDetails.getClientDetails().getId());
                    if(optionalClientDetails.isEmpty())
                        throw new EntityNotFoundException("Client Details not present");

                    ClientDetails clientDetails = optionalClientDetails.get();
                    clientDetails.setClientName(interviewDetailsDto.getClientName());
                    clientDetails.setClientCode(interviewDetailsDto.getProjectCode());
                    clientDetails.setRowUpdtDate(new Date());
                    clientDetailRepository.save(clientDetails);

                    InterviewDetails updatedInterviewDetails = clientInterviewRepository.save(existingInterviewDetails);

                    return new InterviewDetailsDto(updatedInterviewDetails.getId(),
                            updatedInterviewDetails.getClientDetails().getClientName(),
                            updatedInterviewDetails.getProjectCode(),
                            updatedInterviewDetails.getInterviewType(),
                            updatedInterviewDetails.getAssociateName(),
                            updatedInterviewDetails.getKbPageLink(),
                            updatedInterviewDetails.getComments(),
                            updatedInterviewDetails.isUpdatedKbPage(),
                            updatedInterviewDetails.isRetired(),
                            updatedInterviewDetails.getInterviewDate());
                } else {
                    throw new EntityNotFoundException("InterviewDetails with ID " + id + " not found");
                }
            }

    @Override
    @Transactional
    public void deleteInterview(Long id) {
        Optional<InterviewDetails> optionalInterviewDetails = clientInterviewRepository.findById(id);
        if(optionalInterviewDetails.isEmpty())
            throw new EntityNotFoundException("Unable to Find Interview Details with id - "+ id);

        InterviewDetails existingInterviewDetails = optionalInterviewDetails.get();

        ClientDetails existingClientDetails = existingInterviewDetails.getClientDetails();
        if(Objects.isNull(existingClientDetails))
            throw new EntityNotFoundException("Unable to Find Client Details");

        existingClientDetails.setRowUpdtDate(new Date());
        existingClientDetails.setRowEndDate(new Date());
        clientDetailRepository.save(existingClientDetails);

        existingInterviewDetails.setRowUpdtDate(new Date());
        existingInterviewDetails.setRowEndDate(new Date());
        clientInterviewRepository.save(existingInterviewDetails);
    }


    @Override
    @Transactional
    public InterviewDetailsDto createInterview(InterviewDetailsDto interviewDetailsDto) {

        if(Objects.isNull(interviewDetailsDto))
            throw new EntityNotFoundException("Interview Details is Empty");

        ClientDetails clientDetails = ClientDetails.builder()
                .clientName(interviewDetailsDto.getClientName())
                .clientCode(interviewDetailsDto.getProjectCode())
                .createdDate(new Date())
                .rowUpdtDate(new Date())
                .build();
        ClientDetails saveClientDetails = clientDetailRepository.save(clientDetails);
        InterviewDetails saveInterviewDetails =
                clientInterviewRepository.save(InterviewDetails.builder()
                .projectCode(interviewDetailsDto.getProjectCode())
                .clientDetails(saveClientDetails)
                .interviewType(interviewDetailsDto.getInterviewType())
                .associateName(interviewDetailsDto.getAssociateName())
                .kbPageLink(interviewDetailsDto.getKbPageLink())
                .comments(interviewDetailsDto.getComments())
                .updatedKbPage(interviewDetailsDto.isUpdatedKbPage())
                .retired(false)
                .interviewDate(interviewDetailsDto.getInterviewDate())
                .createdDate(new Date()).rowUpdtDate(new Date())
                .build());

        return new InterviewDetailsDto(saveInterviewDetails.getId(),
                        saveClientDetails.getClientName(),
                        saveInterviewDetails.getProjectCode(),
                        saveInterviewDetails.getInterviewType(),
                        saveInterviewDetails.getAssociateName(),
                        saveInterviewDetails.getKbPageLink(),
                        saveInterviewDetails.getComments(),
                        saveInterviewDetails.isUpdatedKbPage(),
                        saveInterviewDetails.isRetired(),
                        saveInterviewDetails.getInterviewDate());
    }
    @Override
    public Page<InterviewDetails> searchInterviewDetails(InterviewDetailsSearchRequestDto searchRequest) {
        PageRequest pageRequest = createPageRequest(searchRequest);
        if (StringUtils.hasText(searchRequest.getSearchString())) {
            return clientInterviewRepository.findBySearchCriteria(searchRequest.getSearchString(), pageRequest);
        } else {
            return clientInterviewRepository.findAll(pageRequest);
        }
    }

    @Override
    public InterviewDetailsDto getInterviewDetailsById(Long id) {
        Optional<InterviewDetails> optionalInterviewDetails = clientInterviewRepository.findById(id);
        if(optionalInterviewDetails.isEmpty())
            throw new DetailsNotFoundException("Interview Details not present with the given Id - "+id);

        InterviewDetails interviewDetails = optionalInterviewDetails.get();
        return new InterviewDetailsDto(interviewDetails.getId(),
                interviewDetails.getClientDetails().getClientName(),
                interviewDetails.getProjectCode(),
                interviewDetails.getInterviewType(),
                interviewDetails.getAssociateName(),
                interviewDetails.getKbPageLink(),
                interviewDetails.getComments(),
                interviewDetails.isUpdatedKbPage(),
                interviewDetails.isRetired(),
                interviewDetails.getInterviewDate());
    }

    @Override
    public List<InterviewDetails> getAttendedInterviewDetails(String associateName) {
        return Collections.emptyList();
    }

    @Override
    public List<InterviewDetails> getAttendedCandidateDetails(String projectCode) {
        return Collections.emptyList();
    }

    private PageRequest createPageRequest(InterviewDetailsSearchRequestDto searchRequest) {
        int offset = Optional.ofNullable(searchRequest.getOffset()).orElse(0);
        int limit = Optional.ofNullable(searchRequest.getLimit()).orElse(DEFAULT_PAGE_SIZE);
        int count = (int) clientInterviewRepository.count();
        offset = Math.min(offset, count);
        limit = Math.min(limit, count);
        Sort sort = createSortFromRequest(searchRequest);
        return PageRequest.of(offset, limit, sort);
    }

    private Sort createSortFromRequest(InterviewDetailsSearchRequestDto searchRequest) {
        String sortField = Optional.ofNullable(searchRequest.getSortField()).orElse(DEFAULT_SORTING_FIELD);
        SortType sortType = Optional.ofNullable(searchRequest.getSortType()).orElse(SortType.DESC);
        return Sort.by(sortType == SortType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
    }



}
