package com.portal.backend.clientinterviewtracker.controller;

import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsDto;
import com.portal.backend.clientinterviewtracker.dto.InterviewDetailsSearchRequestDto;
import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
import com.portal.backend.clientinterviewtracker.service.ClientInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/interview-tracker")
public class ClientInterviewController {

    @Autowired
    ClientInterviewService clientInterviewService;

    @PostMapping("/interviews")
    public ResponseEntity<InterviewDetailsDto> createNewInterviewDetail(@RequestBody InterviewDetailsDto interviewDetailsDto){
        InterviewDetailsDto createdInterviewDetailsDto = clientInterviewService.createInterview(interviewDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInterviewDetailsDto);
    }

    @GetMapping("/interviews")
    public ResponseEntity<Page<InterviewDetailsDto>> getInterviewDetails(Pageable pageable) {

        Page<InterviewDetailsDto> interviewDetailsDtoPage = clientInterviewService.getAllInterviewDetails(pageable);

        return new ResponseEntity<>(interviewDetailsDtoPage, HttpStatus.OK);
    }

    @GetMapping("/interviews/{id}")
    public ResponseEntity<InterviewDetailsDto> getInterviewDetailsById(@PathVariable Long id){
        if(Objects.isNull(id))
            throw new NoSuchElementException(id + " - Id is not present ");
        return ResponseEntity.ok(clientInterviewService.getInterviewDetailsById(id));
    }

    @PutMapping("/interviews/{id}")
    public ResponseEntity<InterviewDetailsDto> updateInterviewDetail(@PathVariable Long id, @RequestBody InterviewDetailsDto interviewDetailsDto){
        InterviewDetailsDto updatedInterviewDetails = clientInterviewService.updateInterview(id, interviewDetailsDto);
        return ResponseEntity.ok(updatedInterviewDetails);
    }

    @DeleteMapping("/interviews/{id}")
    public String deleteInterviewDetail(@PathVariable Long id){
        if(Objects.isNull(id))
            throw new NoSuchElementException(id + "Id is not present");
        clientInterviewService.deleteInterview(id);
        return "Record deleted sucessfully";
    }

    @PostMapping("/interviews/search")
    public ResponseEntity<Page<InterviewDetails>> searchInterviewDetails(@RequestBody InterviewDetailsSearchRequestDto searchRequest) {
        Page<InterviewDetails> interviewDetailsList = clientInterviewService.searchInterviewDetails(searchRequest);
        return new ResponseEntity<>(interviewDetailsList, HttpStatus.OK);
    }

    @GetMapping("/attendedInterviewDetails/{associate_name}")
    public ResponseEntity<List<InterviewDetails>> getAttendedInterviewDetails(@PathVariable(value = "associate_name") String associateName){
        return ResponseEntity.ok(clientInterviewService.getAttendedInterviewDetails(associateName));
    }

    @GetMapping("/attendedCandidateDetails/{project_code}")
    public ResponseEntity<List<InterviewDetails>> getAttendedCandidateDetails(@PathVariable(value = "project_code") String projectCode){
        return ResponseEntity.ok(clientInterviewService.getAttendedCandidateDetails(projectCode));
    }

}
