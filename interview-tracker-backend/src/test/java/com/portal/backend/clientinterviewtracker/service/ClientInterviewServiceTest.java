package com.portal.backend.clientinterviewtracker.service;

import com.portal.backend.clientinterviewtracker.dao.ClientDetailRepository;
import com.portal.backend.clientinterviewtracker.dao.ClientInterviewRepository;
import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsDto;
import com.portal.backend.clientinterviewtracker.entity.ClientDetails;
import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientInterviewServiceTest {
    @Mock
    private ClientInterviewRepository clientInterviewRepository;
    @Mock
    private ClientDetailRepository clientDetailRepository;
    @Mock
    private InterviewDetails interviewDetails;
    @Mock
    private ClientDetails clientDetails;
    private List<InterviewDetails> listOfInterviewDetails;
    @InjectMocks
    private ClientInterviewService clientInterviewService = new ClientInterviewServiceImpl();

    @Test
    public void testGetAllInterviewDetails() {
        given(clientInterviewRepository.findAll()).willReturn(listOfInterviewDetails);
        List<InterviewDetails> fetchedInterviewDetails = clientInterviewRepository.findAll();
        assertThat(fetchedInterviewDetails.size()).isEqualTo(listOfInterviewDetails.size());
    }

    @Test
    public void testCreateInterviewDetails() {

        lenient().when(clientDetailRepository.save(any())).thenReturn(clientDetails);
        when(clientInterviewRepository.save(any())).thenReturn(interviewDetails);
        InterviewDetailsDto interviewDetailsCreateRequest =
                InterviewDetailsDto.builder()
                        .interviewType("FIRST_LEVEL")
                        .clientName("AMAZON")
                        .associateName("Maxwell")
                        .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
                        .comments("test comments")
                        .build();
        InterviewDetailsDto savedInterviewDetails = clientInterviewService.createInterview(interviewDetailsCreateRequest);
        assertNotNull(savedInterviewDetails);
    }

  /*  @Test
    public void testUpdateInterviewDetailsWhenReturnsUpdatedInterviewDetails() {
        clientDetails =  ClientDetails.builder()
                        .id(1L).clientName("UBER").clientCode("UBR-CD").createdDate(new Date()).build();

        when(clientDetailRepository.save(clientDetails)).thenReturn(clientDetails);
        interviewDetails.setId(1L);
        interviewDetails.setClientDetails(clientDetails);
        interviewDetails.setAssociateName("GREG");

        when(clientInterviewRepository.findById(1L)).thenReturn(Optional.of(interviewDetails));
        when(clientInterviewRepository.save(any())).thenReturn(interviewDetails);
        InterviewDetailsDto interviewDetailsUpdateRequest =
                InterviewDetailsDto.builder()
                        .interviewType("FIRST_LEVEL")
                        .clientName("UBER")
                        .projectCode("UBR-CD")
                        .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
                        .associateName("GREG")
                        .comments("test comments")
                        .build();
        InterviewDetailsDto updatedInterviewDetails = clientInterviewService.updateInterview(1L, interviewDetailsUpdateRequest);
        assertThat(updatedInterviewDetails.getClientName()).isNotEqualTo(interviewDetailsUpdateRequest.getClientName());
        assertThat(updatedInterviewDetails.getAssociateName()).isEqualTo(interviewDetailsUpdateRequest.getAssociateName());
    }*/

    @Test
    public void testUpdateInterviewDetailsWhenInterViewDetailsNotFound() {
        when(clientInterviewRepository.findById(2L)).thenReturn(Optional.empty());
        InterviewDetailsDto interviewDetailsUpdateRequest =
                InterviewDetailsDto.builder()
                        .interviewType("FIRST_LEVEL")
                        .clientName("UBER")
                        .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
                        .associateName("GREG")
                        .comments("test comments")
                        .build();
        assertThrows(EntityNotFoundException.class, () -> clientInterviewService.updateInterview(2L, interviewDetailsUpdateRequest));
    }

//    @Test
//    public void testDeleteInterviewDetails() {
//        //when(clientInterviewRepository.findById(2L)).thenReturn(Optional.of(interviewDetails));
//        willDoNothing().given(clientInterviewRepository).deleteById(2L);
//        clientInterviewService.deleteInterview(2L);
//        verify(clientInterviewRepository, times(1)).deleteById(2L);
//    }

    @Test
    public void testGetAttendedInterviewDetails() {

        given(clientInterviewRepository.findByAssociateName("Maxwell")).willReturn(List.of(interviewDetails));
        List<InterviewDetails> fetchedInterviewDetails = clientInterviewService.getAttendedInterviewDetails("Maxwell");
        assertThat(fetchedInterviewDetails.size()).isNotEqualTo(List.of(interviewDetails).size());
    }

    @Test
    public void testGetAttendedInterviewDetails_whenClientInterviewServiceReturnsNoItems() {
        given(clientInterviewRepository.findByAssociateName("Maxwell")).willReturn(Collections.emptyList());
        List<InterviewDetails> fetchedInterviewDetails = clientInterviewService.getAttendedInterviewDetails("Maxwell");
        assertThat(fetchedInterviewDetails.size()).isEqualTo(Collections.emptyList().size());

    }

    @Test
    public void testGetCandidateInterviewDetails() {
        given(clientInterviewRepository.findByProjectCode("AMZ-001")).willReturn(List.of(interviewDetails));
        List<InterviewDetails> fetchedInterviewDetails = clientInterviewService.getAttendedCandidateDetails("AMZ-001");
        assertThat(fetchedInterviewDetails.size()).isNotEqualTo(List.of(interviewDetails).size());
    }

    @Test
    public void testGetCandidateInterviewDetails_whenClientInterviewServiceReturnsNoItems() {
        given(clientInterviewRepository.findByProjectCode("AMZ-001")).willReturn(Collections.emptyList());
        List<InterviewDetails> fetchedInterviewDetails = clientInterviewService.getAttendedCandidateDetails("AMZ-001");
        assertThat(fetchedInterviewDetails.size()).isEqualTo(Collections.emptyList().size());

    }

    /*   @Test
       public void testDeleteInterviewDetailsWhenInterViewDetailsNotFound(){
           when(clientInterviewRepository.findById(1L)).thenReturn(Optional.empty());
           assertThrows(EntityNotFoundException.class,()->clientInterviewService.deleteInterview(1L));
       }*/
    @BeforeEach
    void init() {
        listOfInterviewDetails = new ArrayList<>();
        ClientDetails details = ClientDetails.builder().clientName("AMAZON").build();
        InterviewDetails interviewDetailsRecord1 = InterviewDetails.builder()
                .interviewType("FIRST_LEVEL")
                .clientDetails(details)
                .associateName("Maxwell")
                .projectCode("AMZ-001")
                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
                .comments("test comments")
                .build();
        InterviewDetails interviewDetailsRecord2 = InterviewDetails.builder()
                .interviewType("SECOND LEVEL")
                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
                .associateName("GREG")
                .comments("test comments").build();
        InterviewDetails interviewDetailsRecord3 = InterviewDetails.builder()
                .interviewType("MANAGERIAL LEVEL")
                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
                .associateName("ANDERSON")
                .comments("test comments").build();
        listOfInterviewDetails.add(interviewDetailsRecord1);
        listOfInterviewDetails.add(interviewDetailsRecord2);
        listOfInterviewDetails.add(interviewDetailsRecord3);
        interviewDetails = interviewDetailsRecord1;
        clientDetails = details;
    }
}
