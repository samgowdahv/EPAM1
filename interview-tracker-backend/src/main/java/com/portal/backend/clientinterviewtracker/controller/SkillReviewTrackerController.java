package com.portal.backend.clientinterviewtracker.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.portal.backend.clientinterviewtracker.dao.SkillReviewTrackerRepository;
import com.portal.backend.clientinterviewtracker.entity.*;
import com.portal.backend.clientinterviewtracker.service.BackupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.portal.backend.clientinterviewtracker.dto.SkillReviewTrackerDto;
import com.portal.backend.clientinterviewtracker.service.SkillReviewTrackerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin
@RequestMapping("/skill-tracker")
@RestController
@SecurityRequirement(name = "bearerAuth")
public class SkillReviewTrackerController {

	@Autowired
	private SkillReviewTrackerService skillReviewTrackerService;

	@Operation(summary = "Get all status details")
	@GetMapping("/status")
	public ResponseEntity<List<StatusDetails>> getStatusInfo() {
		return new ResponseEntity<>(skillReviewTrackerService.getAllStatusDetails(), HttpStatus.OK);
	}

	@Operation(summary = "Create a new status detail")
	@PostMapping("/status")
	@Transactional
	public ResponseEntity<String> createStatus(@RequestBody StatusDetails statusDetails) {
		String response = skillReviewTrackerService.saveStatusDetails(statusDetails);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a status detail")
	@PutMapping("/status/{id}")
	@Transactional
	public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody StatusDetails statusDetails) {
		String response = skillReviewTrackerService.updateStatusDetails(id, statusDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Delete a status detail")
	@DeleteMapping("/status/{id}")
	@Transactional
	public ResponseEntity<String> deleteStatus(@PathVariable Long id) {
		String response = skillReviewTrackerService.deleteStatusDetailsById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get all priority details")
	@GetMapping("/priorities")
	public ResponseEntity<List<PriorityDetails>> getPriorities() {
		return new ResponseEntity<>(skillReviewTrackerService.getAllPriorities(), HttpStatus.OK);
	}

	@Operation(summary = "Create a new priority detail")
	@PostMapping("/priorities")
	@Transactional
	public ResponseEntity<String> createPriorities(@RequestBody PriorityDetails priorityDetails) {
		String response = skillReviewTrackerService.savePriorityDetails(priorityDetails);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a priority detail")
	@PutMapping("/priorities/{id}")
	@Transactional
	public ResponseEntity<String> updatePriorities(@PathVariable Long id, @RequestBody PriorityDetails priorityDetails) {
		String response = skillReviewTrackerService.updatePrioritiesDetails(id, priorityDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Delete a priority detail")
	@DeleteMapping("/priorities/{id}")
	@Transactional
	public ResponseEntity<String> deletePriorities(@PathVariable Long id) {
		String response = skillReviewTrackerService.deletePrioritiesById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get all review details")
	@GetMapping("/reviews")
	public ResponseEntity<List<ReviewDetails>> getReviews() {
		return new ResponseEntity<>(skillReviewTrackerService.getReviews(), HttpStatus.OK);
	}

	@Operation(summary = "Create a new review detail")
	@PostMapping("/reviews")
	@Transactional
	public ResponseEntity<String> createReviews(@RequestBody ReviewDetails reviewDetails) {
		String response = skillReviewTrackerService.saveReviewDetails(reviewDetails);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a review detail")
	@PutMapping("/reviews/{id}")
	@Transactional
	public ResponseEntity<String> updateReviews(@PathVariable Long id, @RequestBody ReviewDetails reviewDetails) {
		String response = skillReviewTrackerService.updateReviewDetails(id, reviewDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Delete a review detail")
	@DeleteMapping("/reviews/{id}")
	@Transactional
	public ResponseEntity<String> deleteReviews(@PathVariable Long id) {
		String response = skillReviewTrackerService.deleteReviewDetailsById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Get all level details")
	@GetMapping("/levels")
	public ResponseEntity<List<LevelDetails>> getLevels() {
		return new ResponseEntity<>(skillReviewTrackerService.getLevels(), HttpStatus.OK);
	}

	@Operation(summary = "Create a new level detail")
	@PostMapping("/levels")
	@Transactional
	public ResponseEntity<String> createLevels(@RequestBody LevelDetails levelDetails) {
		String response = skillReviewTrackerService.saveLevelDetails(levelDetails);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a level detail")
	@PutMapping("/levels/{id}")
	@Transactional
	public ResponseEntity<String> updateLevels(@PathVariable Long id, @RequestBody LevelDetails levelDetails) {
		String response = skillReviewTrackerService.updateLevelDetails(id, levelDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Delete a level detail")
	@DeleteMapping("/levels/{id}")
	@Transactional
	public ResponseEntity<String> deleteLevels(@PathVariable Long id) {
		String response = skillReviewTrackerService.deleteLevelDetailsById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Create a new skill review tracker")
	@PostMapping("/skillreviewtracker")
	public ResponseEntity<SkillReviewTrackerDto> skillReviewTracker(@RequestBody SkillReviewTrackerDto trackerDetails) {
		return new ResponseEntity<>(skillReviewTrackerService.saveSkillReviewTracker(trackerDetails), HttpStatus.OK);
	}

	@Operation(summary = "Get all skill review tracker details")
	@GetMapping("/skillreview")
	public ResponseEntity<Page<SkillReviewTrackerDto>> getSkillReviewTrackerDetails(Pageable pageable) {
		return new ResponseEntity<>(skillReviewTrackerService.readSkillReviewTracker(pageable), HttpStatus.OK);
	}

	@Operation(summary = "Update a skill review tracker")
	@PutMapping("/skillreviewtracker/{id}")
	public ResponseEntity<SkillReviewTrackerDto> updateSkillTracker(@PathVariable Long id, @RequestBody SkillReviewTrackerDto skillReviewTrackerDto) {
		SkillReviewTrackerDto updatedSkillReviewTrackerDetails = skillReviewTrackerService.updateInterview(id, skillReviewTrackerDto);
		return ResponseEntity.ok(updatedSkillReviewTrackerDetails);
	}
	@Operation(summary = "Delete skill review tracker details with given id")
	@DeleteMapping("/skillreviewtracker/{id}")
	public String deleteSkillReviewDetail(@PathVariable Long id){
		if(Objects.isNull(id))
			throw new NoSuchElementException(id + " - Id is not present ");
		skillReviewTrackerService.deleteSkillReviewDetails(id);
		return "Record deleted sucessfully";
	}

	@Operation(summary = "Create a new incubation detail")
	@PostMapping("/incubation")
	@Transactional
	public ResponseEntity<String> createIncubationDetails(@RequestBody IncubationNeeded incubationDetails) {
		String response = skillReviewTrackerService.saveIncubationDetails(incubationDetails);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Get All incubation detail")
	@GetMapping("/incubations")
	public ResponseEntity<List<IncubationNeeded>> getAllIncubationDetails() {
		List<IncubationNeeded> incubationDetailsList = skillReviewTrackerService.getAllIncubationDetails();
		return new ResponseEntity<>(incubationDetailsList, HttpStatus.OK);
	}

	@Autowired
	private SkillReviewTrackerRepository skillReviewTrackerRepository;

	@Autowired
	private BackupService backupService;

	@PostMapping("/skill-review-tracker-column")
	public String createColumn(){
			backupService.createEmployeeIdColumn();
			return "Column Employee Id created successfully";

	}

	@GetMapping("/find-bkp-skill-tracker")
	public List<SkillReviewTrackerDetails> getAllSkillTrackerDetails() {
		try {
			return skillReviewTrackerRepository.getAllBkpSkillReviewTracker();
		}catch(Exception e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}

}
