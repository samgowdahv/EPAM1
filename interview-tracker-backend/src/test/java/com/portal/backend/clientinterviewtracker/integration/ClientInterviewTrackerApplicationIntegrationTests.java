//package com.portal.backend.clientinterviewtracker.integration;
//
//import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsDto;
//import com.portal.backend.clientinterviewtracker.entity.ClientDetails;
//import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ClientInterviewTrackerApplicationIntegrationTests {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//    private InterviewDetails interviewDetails;
//
//    private ClientDetails clientDetails;
//    private InterviewDetailsDto interviewDetailsDto;
//    private List<InterviewDetails> listOfInterviewDetails;
//
//    @Autowired
//    private ServerProperties serverProperties;
//
//    @Test
//    public void createInterviewDetails() {
//        ResponseEntity<InterviewDetailsDto> responseEntity =
//                restTemplate.postForEntity("/InterviewTracker/createNewInterviewDetail", interviewDetailsDto, InterviewDetailsDto.class);
//        InterviewDetailsDto interviewDetailsresponse = responseEntity.getBody();
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals("AMAZON", interviewDetailsresponse.getClientName());
//    }
//
//    @Test
//    public void updateInterviewDetails() {
//        ResponseEntity<InterviewDetails> responseEntity =
//                restTemplate.postForEntity("/InterviewTracker/createNewInterviewDetail", interviewDetailsDto, InterviewDetails.class);
//        InterviewDetails createdInterviewDetail = responseEntity.getBody();
//        final ResponseEntity<InterviewDetails> response = restTemplate.exchange(
//                String.format(restTemplate.getRootUri() + "/InterviewTracker/updateInterviewDetail" + "/" + createdInterviewDetail.getId()),
//                HttpMethod.PUT,
//                new HttpEntity<>(createdInterviewDetail),
//                InterviewDetails.class
//        );
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("AMAZON", Objects.requireNonNull(response.getBody()).getClientDetails().getClientName());
//    }
//
//    @Test
//    public void testGetAllInterviewDetails() {
//        ResponseEntity<InterviewDetails> responseEntity =
//                restTemplate.postForEntity("/InterviewTracker/createNewInterviewDetail", interviewDetailsDto, InterviewDetails.class);
//        InterviewDetails createdInterviewDetail = responseEntity.getBody();
//        List<InterviewDetails> listOfInterviewDetails =
//                restTemplate.getForObject("/InterviewTracker/getDetails", List.class);
//        assertNotEquals(listOfInterviewDetails.size(), 0);
//    }
//
//    @Test
//    public void testDeleteInterviewDetails() {
//        ResponseEntity<InterviewDetails> responseEntity =
//                restTemplate.postForEntity("/InterviewTracker/createNewInterviewDetail", interviewDetailsDto, InterviewDetails.class);
//        InterviewDetails createdInterviewDetail = responseEntity.getBody();
//        final ResponseEntity<String> response = restTemplate.exchange(
//                String.format(restTemplate.getRootUri() + "/InterviewTracker/deleteInterviewDetail" + "/" + createdInterviewDetail.getId()),
//                HttpMethod.DELETE,
//                new HttpEntity<>(createdInterviewDetail),
//                String.class
//        );
//        assertEquals(response.getStatusCode().value(), 200);
//    }
//
//    @Test
//    public void testGetAttendedInterviewDetails() {
//        ResponseEntity<InterviewDetails> responseEntity =
//                restTemplate.postForEntity("/InterviewTracker/createNewInterviewDetail", interviewDetailsDto, InterviewDetails.class);
//        InterviewDetails createdInterviewDetail = responseEntity.getBody();
//        List<InterviewDetails> listOfInterviewDetails =
//                restTemplate.getForObject("/InterviewTracker/attendedInterviewDetails/Philip", List.class);
//        assertNotEquals(listOfInterviewDetails.size(), 0);
//    }
//
//    @Test
//    public void testGetCandidateInterviewDetails() {
//        ResponseEntity<InterviewDetails> responseEntity =
//                restTemplate.postForEntity("/InterviewTracker/createNewInterviewDetail", interviewDetailsDto, InterviewDetails.class);
//        InterviewDetails createdInterviewDetail = responseEntity.getBody();
//        List<InterviewDetails> listOfInterviewDetails =
//                restTemplate.getForObject("/InterviewTracker/attendedCandidateDetails/AMZ-001", List.class);
//        assertNotEquals(listOfInterviewDetails.size(), 0);
//    }
//
//    @BeforeEach
//    void init() {
//        interviewDetailsDto =
//                InterviewDetailsDto.builder()
//                        .interviewType("FIRST_LEVEL")
//                        .createdDate(new Date())
//                        .clientName("AMAZON")
//                        .associateName("Philip")
//                        .projectCode("AMZ-001")
//                        .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
//                        .comments("test comments")
//                        .build();
//        listOfInterviewDetails = new ArrayList<>();
//        ClientDetails clientDetails =  ClientDetails.builder().clientName("AMAZON").build();
//        InterviewDetails interviewDetailsRecord1 = InterviewDetails.builder()
//                .interviewType("FIRST LEVEL")
//                .associateName("Philip")
//                .clientDetails(clientDetails)
//                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
//                .comments("test comments").build();
//        InterviewDetails interviewDetailsRecord2 = InterviewDetails.builder()
//                .interviewType("SECOND LEVEL")
//                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
//                .associateName("GREG")
//                .clientDetails(clientDetails)
//                .comments("test comments").build();
//        InterviewDetails interviewDetailsRecord3 = InterviewDetails.builder()
//                .interviewType("MANAGERIAL LEVEL")
//                .interviewDate(Date.from(Instant.parse("2024-02-01T00:00:00.000Z")))
//                .associateName("ANDERSON")
//                .clientDetails(clientDetails)
//                .comments("test comments").build();
//        listOfInterviewDetails.add(interviewDetailsRecord1);
//        listOfInterviewDetails.add(interviewDetailsRecord2);
//        listOfInterviewDetails.add(interviewDetailsRecord3);
//        interviewDetails = interviewDetailsRecord1;
//        clientDetails = clientDetails;
//    }
//}
