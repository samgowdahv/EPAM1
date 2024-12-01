//package com.portal.backend.clientinterviewtracker.dao;
//
//import com.portal.backend.clientinterviewtracker.entity.ClientDetails;
//import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@DataJpaTest
//public class ClientInterviewRepositoryTest {
//
//    @Mock
//    ClientInterviewRepository clientInterviewRepository;
//    @Mock
//    ClientDetailRepository clientDetailRepository;
//    private InterviewDetails interviewDetails;
//    private ClientDetails clientDetails;
//    private List<InterviewDetails> listOfInterviewDetails;
//
//    @BeforeEach
//    void init() {
//        listOfInterviewDetails = new ArrayList<>();
//        ClientDetails clientDetails = ClientDetails.builder().clientName("AMAZON").build();
//        InterviewDetails interviewDetailsRecord1 = InterviewDetails.builder()
//                .interviewType("FIRST_LEVEL")
//                .clientDetails(clientDetails)
//                .associateName("Maxwell")
//                .projectCode("AMZ-001")
//                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
//                .comments("test comments")
//                .build();
//        InterviewDetails interviewDetailsRecord2 = InterviewDetails.builder()
//                .interviewType("SECOND LEVEL")
//                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
//                .associateName("GREG")
//                .comments("test comments").build();
//        InterviewDetails interviewDetailsRecord3 = InterviewDetails.builder()
//                .interviewType("MANAGERIAL LEVEL")
//                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
//                .associateName("ANDERSON")
//                .comments("test comments").build();
//        listOfInterviewDetails.add(interviewDetailsRecord1);
//        listOfInterviewDetails.add(interviewDetailsRecord2);
//        listOfInterviewDetails.add(interviewDetailsRecord3);
//        interviewDetails = interviewDetailsRecord1;
//    }
//
//    @Test
//    void testSaveClientInterviewDetails() {
//        when(clientDetailRepository.save(clientDetails)).thenReturn(clientDetails);
//        when(clientInterviewRepository.save(interviewDetails)).thenReturn(interviewDetails);
//        InterviewDetails savedInterviewDetails = clientInterviewRepository.save(interviewDetails);
//        assertNotNull(savedInterviewDetails);
//    }
//
//    @Test
//    void testFindAllClientInterviewDetails() {
//
//        when(clientInterviewRepository.findAll()).thenReturn(listOfInterviewDetails);
//        List<InterviewDetails> interviewDetailsList = clientInterviewRepository.findAll();
//
//        assertThat(listOfInterviewDetails.size()).isEqualTo(interviewDetailsList.size());
//    }
//
//    @Test
//    void testFindAllClientInterviewDetails_when_clientInterviewDetails_are_empty() {
//
//        when(clientInterviewRepository.findAll()).thenReturn(Collections.emptyList());
//        List<InterviewDetails> interviewDetailsList = clientInterviewRepository.findAll();
//
//        assertEquals(Collections.emptyList().size(), interviewDetailsList.size());
//    }
//
//    @Test
//    void testUpdateClientInterviewDetails() {
//        interviewDetails.setId(1l);
//        interviewDetails.setInterviewType("SECOND_LEVEL");
//        when(clientInterviewRepository.findById(1l)).thenReturn(Optional.ofNullable(interviewDetails));
//        when(clientInterviewRepository.save(interviewDetails)).thenReturn(interviewDetails);
//        InterviewDetails updatedInterviewDetails = clientInterviewRepository.save(interviewDetails);
//        assertEquals(interviewDetails.getInterviewType(), updatedInterviewDetails.getInterviewType());
//    }
//
//    @Test
//    void testUpdateClientInterviewDetails_when_interviewDetailsNotFound() {
//        when(clientInterviewRepository.findById(1l)).thenReturn(Optional.empty());
//        Optional<InterviewDetails> clientInterviewDetailsById = clientInterviewRepository.findById(1l);
//        assertTrue(clientInterviewDetailsById.isEmpty());
//
//    }
//
//    @Test
//    void testDeleteClientInterviewDetails() {
//        doNothing().when(clientInterviewRepository).deleteById(1l);
//        clientInterviewRepository.deleteById(1l);
//        verify(clientInterviewRepository, times(1)).deleteById(1l);
//    }
//
//    @Test
//    void testGetByAssociate_clientInterviewDetails() {
//
//        when(clientInterviewRepository.findByAssociateName("Maxwell")).thenReturn(List.of(interviewDetails));
//        List<InterviewDetails> interviewDetailsList = clientInterviewRepository.findByAssociateName("Maxwell");
//
//        assertThat(List.of(interviewDetails).size()).isEqualTo(interviewDetailsList.size());
//    }
//
//    @Test
//    void testGetByProjectCode_clientInterviewDetails() {
//
//        when(clientInterviewRepository.findByProjectCode("AMZ-001")).thenReturn(List.of(interviewDetails));
//        List<InterviewDetails> interviewDetailsList = clientInterviewRepository.findByProjectCode("AMZ-001");
//
//        assertThat(List.of(interviewDetails).size()).isEqualTo(interviewDetailsList.size());
//    }
//
//}
