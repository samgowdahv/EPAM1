//package com.portal.backend.clientinterviewtracker.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsDto;
//import com.portal.backend.clientinterviewtracker.entity.ClientDetails;
//import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
//import com.portal.backend.clientinterviewtracker.service.ClientInterviewService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(ClientInterviewController.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class ClientInterviewControllerTest {
//
//    @Autowired
//    public MockMvc mockMvc;
//
//    @MockBean
//    public ClientInterviewService mockClientInterviewService;
//
//
//    @Test
//    void testCreateNewInterviewDetail() throws Exception {
//        // Setup
//        ClientDetails clientDetails =  ClientDetails.builder().clientName("Goldman").build();
//        InterviewDetailsDto interviewDetailsDtoTest =  InterviewDetailsDto.builder()
//                .clientName("Goldman")
//                .interviewType("Regular")
//                .associateName("XYZ")
//                .kbPageLink("https://kb.epam.com")
//                .comments("Positive")
//                .updatedKbPage(true)
//                .retired(false)
//                .interviewDate(new Date(2024, Calendar.APRIL, 23))
//                .createdDate(new Date(2024, Calendar.FEBRUARY, 12))
//                .lastModifiedDate(new Date(2024, Calendar.FEBRUARY, 15)).build();
//
//        InterviewDetails interviewDetailsTest = InterviewDetails.builder()
//                .clientDetails(clientDetails)
//                .interviewType("Regular")
//                .associateName("XYZ")
//                .kbPageLink("https://kb.epam.com")
//                .comments("Positive")
//                .updatedKbPage(true)
//                .retired(false)
//                .interviewDate(new Date(2024, Calendar.APRIL, 23))
//                .createdDate(new Date(2024, Calendar.FEBRUARY, 12))
//                .lastModifiedDate(new Date(2024, Calendar.FEBRUARY, 15)).build();
//
//
//
//        when(mockClientInterviewService
//                .createInterview(
//                        interviewDetailsDtoTest))
//                .thenReturn(interviewDetailsTest);
//
//        // Run the test
//   /*     final MockHttpServletResponse response = mockMvc.perform(post("/InterviewTracker/createNewInterviewDetail")
//                        .content(asJsonString(interviewDetailsDtoTest))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//        assertThat(response.getContentAsString()).isEqualTo(asJsonString(interviewDetailsTest));
//    }
//
//    @Test
//    void testGetInterviewDetails() throws Exception {
//        // Setup
//        ClientDetails clientDetails =  ClientDetails.builder().clientName("Goldman").build();
//        final List<InterviewDetails> interviewDetails = List.of(InterviewDetails.builder()
//                .id(122L)
//                .clientDetails(clientDetails)
//                .interviewType("Regular")
//                .associateName("XYZ")
//                .kbPageLink("https://kb.epam.com")
//                .comments("Positive")
//                .updatedKbPage(true)
//                .retired(false)
//                .interviewDate(new Date(2024, Calendar.APRIL, 23))
//                .createdDate(new Date(2024, Calendar.FEBRUARY, 12))
//                .lastModifiedDate(new Date(2024, Calendar.FEBRUARY, 15)).build());
//        /*when(mockClientInterviewService.getAllDetails()).thenReturn(interviewDetails);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/InterviewTracker/getDetails")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(asJsonString(interviewDetails));*/
//    }
//
//    @Test
//    void testGetInterviewDetails_ClientInterviewServiceReturnsNoItems() throws Exception {
//        // Setup
//        /*when(mockClientInterviewService.getAllDetails()).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/InterviewTracker/getDetails")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("[]");*/
//    }
//
//    @Test
//    void testUpdateInterviewDetail() throws Exception {
//        // Setup
//        ClientDetails clientDetails =  ClientDetails.builder().clientName("Goldman").build();
//        InterviewDetailsDto interviewDetailsDtoTest =  InterviewDetailsDto.builder()
//                .clientName("Goldman")
//                .interviewType("Regular")
//                .associateName("XYZ")
//                .kbPageLink("https://kb.epam.com")
//                .comments("Negative")
//                .updatedKbPage(true)
//                .retired(false)
//                .interviewDate(new Date(2024, Calendar.APRIL, 23))
//                .createdDate(new Date(2024, Calendar.FEBRUARY, 12))
//                .lastModifiedDate(new Date(2024, Calendar.FEBRUARY, 15)).build();
//
//        InterviewDetails interviewDetailsTest = InterviewDetails.builder()
//                .clientDetails(clientDetails)
//                .interviewType("Regular")
//                .associateName("XYZ")
//                .kbPageLink("https://kb.epam.com")
//                .comments("Negative")
//                .updatedKbPage(true)
//                .retired(false)
//                .interviewDate(new Date(2024, Calendar.APRIL, 23))
//                .createdDate(new Date(2024, Calendar.FEBRUARY, 12))
//                .lastModifiedDate(new Date(2024, Calendar.FEBRUARY, 15)).build();
//
//        when(mockClientInterviewService.updateInterview(122L, interviewDetailsDtoTest))
//                .thenReturn(interviewDetailsTest);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(put("/InterviewTracker/updateInterviewDetail/{id}", 122L)
//                        .content(asJsonString(interviewDetailsDtoTest))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(asJsonString(interviewDetailsTest));
//    }
//
//    @Test
//    void testDeleteInterviewDetail() throws Exception {
//        // Setup
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(delete("/InterviewTracker/deleteInterviewDetail/{id}", 122)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("");
//        verify(mockClientInterviewService).deleteInterview(122L);
//    }
//
//    @Test
//    void testGetAttendedInterviewDetails() throws Exception {
//        // Setup
//        ClientDetails clientDetails =  ClientDetails.builder().clientName("Goldman").build();
//        final List<InterviewDetails> interviewDetails = List.of(InterviewDetails.builder()
//                .id(122L)
//                .clientDetails(clientDetails)
//                .interviewType("Regular")
//                .associateName("XYZ")
//                .kbPageLink("https://kb.epam.com")
//                .comments("Positive")
//                .updatedKbPage(true)
//                .retired(false)
//                .interviewDate(new Date(2024, Calendar.APRIL, 23))
//                .createdDate(new Date(2024, Calendar.FEBRUARY, 12))
//                .lastModifiedDate(new Date(2024, Calendar.FEBRUARY, 15)).build());
//        when(mockClientInterviewService.getAttendedInterviewDetails("XYZ")).thenReturn(interviewDetails);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/InterviewTracker/attendedInterviewDetails/{associate_name}", "XYZ")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(asJsonString(interviewDetails));
//    }
//
//    @Test
//    void testGetAttendedInterviewDetails_ClientInterviewServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockClientInterviewService.getAttendedInterviewDetails("XYZ")).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/InterviewTracker/attendedInterviewDetails/{associate_name}", "XYZ")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("[]");
//    }
//
//    @Test
//    void testGetAttendedCandidateDetails() throws Exception {
//        // Setup
//        ClientDetails clientDetails =  ClientDetails.builder().clientName("Goldman").build();
//        final List<InterviewDetails> interviewDetails = List.of(InterviewDetails.builder()
//                .id(122L)
//                .clientDetails(clientDetails)
//                .interviewType("Regular")
//                .associateName("XYZ")
//                .projectCode("AMZ-001")
//                .kbPageLink("https://kb.epam.com")
//                .comments("Positive")
//                .updatedKbPage(true)
//                .retired(false)
//                .interviewDate(new Date(2024, Calendar.APRIL, 23))
//                .createdDate(new Date(2024, Calendar.FEBRUARY, 12))
//                .lastModifiedDate(new Date(2024, Calendar.FEBRUARY, 15)).build());
//        when(mockClientInterviewService.getAttendedCandidateDetails("AMZ-001")).thenReturn(interviewDetails);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/InterviewTracker/attendedCandidateDetails/{project_code}", "AMZ-001")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(asJsonString(interviewDetails));
//    }
//
//    @Test
//    void testGetAttendedCandidateDetails_ClientInterviewServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockClientInterviewService.getAttendedCandidateDetails("AMZ-001")).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/InterviewTracker/attendedCandidateDetails/{project_code}", "AMZ-001")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("[]");
//    }
//
//
//    public static String asJsonString(final Object obj) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//            return mapper.writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    */
//}
