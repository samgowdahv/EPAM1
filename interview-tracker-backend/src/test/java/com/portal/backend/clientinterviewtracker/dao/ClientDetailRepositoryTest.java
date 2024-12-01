//package com.portal.backend.clientinterviewtracker.dao;
//
//import com.portal.backend.clientinterviewtracker.entity.ClientDetails;
//import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@DataJpaTest
//class ClientDetailRepositoryTest {
//    @Mock
//    ClientDetailRepository clientDetailRepository;
//    private ClientDetails clientDetails;
//    private List<ClientDetails> listOfClientDetails;
//
//    @BeforeEach
//    void init(){
//        listOfClientDetails = new ArrayList<>();
//        ClientDetails clientDetailsOne = ClientDetails.builder().clientName("AMAZON").
//                clientCode("121").build();
//        ClientDetails clientDetailsTwo = ClientDetails.builder().clientName("GOOGLE").
//                clientCode("122").build();
//        listOfClientDetails.add(clientDetailsOne);
//        listOfClientDetails.add(clientDetailsTwo);
//        clientDetails = clientDetailsOne;
//
//    }
//
//    @Test
//    void testSaveClientInterviewDetails() {
//
//        when(clientDetailRepository.save(clientDetails)).thenReturn(clientDetails);
//        ClientDetails savedClientDetails = clientDetailRepository.save(clientDetails);
//        assertNotNull(savedClientDetails);
//    }
//}
