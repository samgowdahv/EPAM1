package com.portal.backend.clientinterviewtracker.service;

import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsDto;
import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsSearchRequestDto;
import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientInterviewService {
    InterviewDetailsDto createInterview(InterviewDetailsDto interviewDetailsDto);

    Page<InterviewDetailsDto> getAllInterviewDetails(Pageable pageable);

    InterviewDetailsDto updateInterview(Long id, InterviewDetailsDto interviewDetailsDto);

    void deleteInterview(Long id);
    Page<InterviewDetails> searchInterviewDetails(InterviewDetailsSearchRequestDto searchRequest);

    InterviewDetailsDto getInterviewDetailsById(Long id);

    List<InterviewDetails> getAttendedInterviewDetails(String associateName);

    List<InterviewDetails> getAttendedCandidateDetails(String projectCode);
}
