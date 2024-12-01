package com.portal.backend.clientinterviewtracker.service;

import java.util.*;
import java.util.stream.Collectors;

import com.portal.backend.clientinterviewtracker.dao.*;
import com.portal.backend.clientinterviewtracker.entity.*;
import com.portal.backend.clientinterviewtracker.mapper.SkillReviewTrackerMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.backend.clientinterviewtracker.dto.SkillReviewTrackerDto;


@Service
public class SkillReviewTrackerServiceImpl implements SkillReviewTrackerService {
	@Autowired
	StatusDetailRepository statusDetailRepository;

	@Autowired
	PriorityDetailRepository priorityDetailRepository;

	@Autowired
	ReviewDetailsRepository reviewDetailsRepository;

	@Autowired
	LevelDetailsRepository levelDetailsRepository;

	@Autowired
	SkillReviewTrackerRepository skillReviewTrackerRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SkillReviewTrackerMapper skillReviewTrackerMapper;

	@Autowired
	private IncubationNeededRepository incubationNeededRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IncubationRepository incubationRepository;

	@Override
	public List<StatusDetails> getAllStatusDetails() {
		return statusDetailRepository.findAll();

	}

	@Override
	public List<PriorityDetails> getAllPriorities() {
		return priorityDetailRepository.findAll();

	}

	@Override
	public List<ReviewDetails> getReviews() {
		return reviewDetailsRepository.findAll();
	}

	@Override
	public List<LevelDetails> getLevels() {
		return levelDetailsRepository.findAll();
	}

	@Override
	@Transactional
	public SkillReviewTrackerDto saveSkillReviewTracker(SkillReviewTrackerDto trackerDetails) {
		try {
			if(Objects.isNull(trackerDetails))
				throw new EntityNotFoundException("Skill Review Tracker Dto is empty");

			SkillReviewTrackerDetails skillReviewTrackerDetails = new SkillReviewTrackerDetails();

			skillReviewTrackerDetails.setEmployeeId(trackerDetails.getEmployeeId());
			skillReviewTrackerDetails.setEmployeeName(trackerDetails.getEmployeeName());
			skillReviewTrackerDetails.setSkillReviewType(trackerDetails.getSkillReviewType());
			skillReviewTrackerDetails.setLevels(trackerDetails.getLevels());
			skillReviewTrackerDetails.setPriority(trackerDetails.getPriority());
			skillReviewTrackerDetails.setRequestDate(trackerDetails.getRequestDate());
			skillReviewTrackerDetails.setStatus(trackerDetails.getStatus());
			skillReviewTrackerDetails.setCompletionDate(trackerDetails.getCompletionDate());
			skillReviewTrackerDetails.setRating(trackerDetails.getRating());
			skillReviewTrackerDetails.setAction(trackerDetails.getAction());
			skillReviewTrackerDetails.setComments(trackerDetails.getComments());
			skillReviewTrackerDetails.setReviewer(trackerDetails.getReviewer());
			skillReviewTrackerDetails.setCreatedDate(new Date());
			skillReviewTrackerDetails.setPlannedDate(trackerDetails.getPlannedDate());
			skillReviewTrackerDetails.setRowUpdtDate(new Date());

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User updatedBy = (User) authentication.getPrincipal();

			Optional<com.portal.backend.clientinterviewtracker.entity.User> user = userRepository.findByEmail(updatedBy.getUsername());
			user.ifPresent(value -> skillReviewTrackerDetails.setCreatedByUser(value.getEmail()));

			SkillReviewTrackerDetails saveSkillReviewTrackerDetails = skillReviewTrackerRepository.save(skillReviewTrackerDetails);

			if (Objects.nonNull(trackerDetails.getIncubation())) {

				List<String> incubationRequestList = Arrays.stream(trackerDetails.getIncubation().split("\\s*,\\s*")).map(String::trim).toList();
				List<IncubationNeeded> incubationDetailsList = incubationNeededRepository.findByNameIn(incubationRequestList);

				incubationDetailsList.forEach(incubationNeeded -> {
					Incubation incubation = new Incubation();
					incubation.setEmployeeId(saveSkillReviewTrackerDetails.getEmployeeId());
					incubation.setIncubationSkill(incubationNeeded.getName());
					incubation.setIncubationAdded(true);
					incubationRepository.save(incubation);
				});
			}

			return skillReviewTrackerMapper.toDto(saveSkillReviewTrackerDetails);
		}catch (Exception e){
			throw new EntityNotFoundException(e.getMessage());
		}

	}

	@Override
	public Page<SkillReviewTrackerDto> readSkillReviewTracker(Pageable pageable) {
		try {
			Page<SkillReviewTrackerDetails> skillReviewTrackerDetailsPageList = skillReviewTrackerRepository.findAllByRowEndDateIsNullOrderByCreatedDateDesc(pageable);
			if (skillReviewTrackerDetailsPageList.isEmpty() || skillReviewTrackerDetailsPageList.getSize() == 0)
				throw new EntityNotFoundException("Skill Review Details not present");

			List<SkillReviewTrackerDto> skillReviewTrackerDtoList = skillReviewTrackerMapper.getSkillReviewTrackerDetailsDto(skillReviewTrackerDetailsPageList);

			int totalRecord = skillReviewTrackerRepository.findAllByRowEndDateIsNullOrderByCreatedDateDesc().size();
			return new PageImpl<>(skillReviewTrackerDtoList, skillReviewTrackerDetailsPageList.getPageable(), totalRecord);
		} catch (Exception e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public SkillReviewTrackerDto updateInterview(Long id, SkillReviewTrackerDto skillReviewTrackerDto) {

		try {
			Optional<SkillReviewTrackerDetails> optionalSkillReviewDetails = skillReviewTrackerRepository.findById(id);
			if(optionalSkillReviewDetails.isEmpty())
				throw new EntityNotFoundException("Skill Review Tracker Details not present");

			SkillReviewTrackerDetails existingSkillReviewDetails = optionalSkillReviewDetails.get();

			existingSkillReviewDetails.setEmployeeId(skillReviewTrackerDto.getEmployeeId());
			existingSkillReviewDetails.setEmployeeName(skillReviewTrackerDto.getEmployeeName());
			existingSkillReviewDetails.setSkillReviewType(skillReviewTrackerDto.getSkillReviewType());
			existingSkillReviewDetails.setLevels(skillReviewTrackerDto.getLevels());
			existingSkillReviewDetails.setPriority(skillReviewTrackerDto.getPriority());
			existingSkillReviewDetails.setRequestDate(skillReviewTrackerDto.getRequestDate());
			existingSkillReviewDetails.setStatus(skillReviewTrackerDto.getStatus());
			existingSkillReviewDetails.setCompletionDate(skillReviewTrackerDto.getCompletionDate());
			existingSkillReviewDetails.setRating(skillReviewTrackerDto.getRating());
			existingSkillReviewDetails.setAction(skillReviewTrackerDto.getAction());
			existingSkillReviewDetails.setComments(skillReviewTrackerDto.getComments());
			existingSkillReviewDetails.setReviewer(skillReviewTrackerDto.getReviewer());
			existingSkillReviewDetails.setPlannedDate(skillReviewTrackerDto.getPlannedDate());
			existingSkillReviewDetails.setRowUpdtDate(new Date());

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User updatedBy = (User) authentication.getPrincipal();

			Optional<com.portal.backend.clientinterviewtracker.entity.User> user = userRepository.findByEmail(updatedBy.getUsername());
			user.ifPresent(value -> existingSkillReviewDetails.setLastModifiedByUser(value.getEmail()));

			SkillReviewTrackerDetails skillReviewTrackerDetails = skillReviewTrackerRepository.save(existingSkillReviewDetails);

			List<Incubation> incubationList = incubationRepository.findByEmployeeIdIn(List.of(existingSkillReviewDetails.getEmployeeId()));
			if (Objects.isNull(skillReviewTrackerDto.getIncubation()) || StringUtils.isBlank(skillReviewTrackerDto.getIncubation())) {
				incubationList.forEach(incubation -> {
					incubation.setIncubationAdded(false);
					incubationRepository.save(incubation);
				});
				return skillReviewTrackerMapper.toDto(skillReviewTrackerDetails);
			}

			List<Incubation> existingSkills = incubationRepository.findByEmployeeId(skillReviewTrackerDto.getEmployeeId());
			List<String> newSkills = Arrays.stream(skillReviewTrackerDto.getIncubation().split("\\s*,\\s*")).map(String::trim).toList();

			//Set all existing skills false if not present in the new skills
			existingSkills.forEach(skill -> {
				if (!newSkills.contains(skill.getIncubationSkill())) {
					skill.setIncubationAdded(false);
				}
			});

			//Add new skills update existing ones
			for (String skillName : newSkills) {
				Optional<Incubation> optionalSkill = incubationRepository.findByEmployeeIdAndIncubationSkill(skillReviewTrackerDto.getEmployeeId(), skillName);
				if (optionalSkill.isPresent()) {
					Incubation skill = optionalSkill.get();
					skill.setIncubationAdded(true);
				} else {
					Incubation newSkill = new Incubation();
					newSkill.setEmployeeId(skillReviewTrackerDto.getEmployeeId());
					newSkill.setIncubationSkill(skillName);
					newSkill.setIncubationAdded(true);
					incubationRepository.save(newSkill);
				}
			}
			incubationRepository.saveAll(existingSkills);
			return skillReviewTrackerMapper.toDto(skillReviewTrackerDetails);
		}catch (Exception e){
			throw new EntityNotFoundException(e.getMessage());
		}

	}

	@Transactional
	public String saveStatusDetails(StatusDetails statusDetails) {
		statusDetailRepository.save(statusDetails);
		return "Status saved successfully.";
	}

	@Transactional
	public String updateStatusDetails(Long id, StatusDetails statusDetails) {
		StatusDetails existingStatus = statusDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Status with ID " + id + " not found."));
		existingStatus.setStatus(statusDetails.getStatus());
		statusDetailRepository.save(existingStatus);
		return "Status updated successfully.";
	}

	@Transactional
	public String deleteStatusDetailsById(Long id) {
		if (!statusDetailRepository.existsById(id))
			throw new RuntimeException("Status with ID " + id + " not found.");
		statusDetailRepository.deleteById(id);
		return "Status deleted successfully.";
	}
	
	@Transactional
	public String savePriorityDetails(PriorityDetails priorityDetails) {
		priorityDetailRepository.save(priorityDetails);
		return "PriorityDetails saved successfully.";
	}

	@Transactional
	public String updatePrioritiesDetails(Long id, PriorityDetails priorityDetails) {
		PriorityDetails existingPriorityDetails = priorityDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("PriorityDetails with ID " + id + " not found."));
		existingPriorityDetails.setPriority(priorityDetails.getPriority());
		priorityDetailRepository.save(existingPriorityDetails);
		return "PriorityDetail updated successfully.";
	}

	@Transactional
	public String deletePrioritiesById(Long id) {
		if (!priorityDetailRepository.existsById(id))
			throw new RuntimeException("PriorityDetails with ID " + id + " not found.");
		priorityDetailRepository.deleteById(id);
		return "PriorityDetail deleted successfully.";
	}
	
	@Transactional
	public String saveReviewDetails(ReviewDetails reviewDetails) {
		reviewDetailsRepository.save(reviewDetails);
		return "ReviewDetails saved successfully.";
	}

	@Transactional
	public String updateReviewDetails(Long id, ReviewDetails reviewDetails) {
		ReviewDetails existingReviewDetails = reviewDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("ReviewDetails with ID " + id + " not found."));
		existingReviewDetails.setReviewType(reviewDetails.getReviewType());
		reviewDetailsRepository.save(existingReviewDetails);
		return "ReviewDetail updated successfully.";
	}

	@Transactional
	public String deleteReviewDetailsById(Long id) {
		if (!reviewDetailsRepository.existsById(id))
			throw new RuntimeException("ReviewDetails with ID " + id + " not found.");
		reviewDetailsRepository.deleteById(id);
		return "ReviewDetail deleted successfully.";
	}
	
	@Transactional
	public String saveLevelDetails(LevelDetails levelDetails) {
		levelDetailsRepository.save(levelDetails);
		return "LevelDetails saved successfully.";
	}

	@Transactional
	public String updateLevelDetails(Long id, LevelDetails levelDetails) {
		LevelDetails existingLevelDetails = levelDetailsRepository.findByLevelId(id).orElseThrow(() -> new RuntimeException("LevelDetails with ID " + id + " not found."));
		existingLevelDetails.setLevel(levelDetails.getLevel());
		levelDetailsRepository.save(existingLevelDetails);
		return "LevelDetail updated successfully.";
	}

	@Transactional
	public String deleteLevelDetailsById(Long id) {
		if (!levelDetailsRepository.existsById(id))
			throw new RuntimeException("LevelDetails with ID " + id + " not found.");
		levelDetailsRepository.deleteById(id);
		return "LevelDetail deleted successfully.";
	}

	@Override
	@Transactional
	public void deleteSkillReviewDetails(Long id) {
		try {
			Optional<SkillReviewTrackerDetails> optionalSkillReviewTrackerDetails = skillReviewTrackerRepository.findById(id);
            if(optionalSkillReviewTrackerDetails.isEmpty())
				
				throw new EntityNotFoundException("Skill Review Details not present");

			SkillReviewTrackerDetails existingSkillReviewTrackerDetails = optionalSkillReviewTrackerDetails.get();
			existingSkillReviewTrackerDetails.setRowUpdtDate(new Date());
			existingSkillReviewTrackerDetails.setRowEndDate(new Date());

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User deletedBy = (User) authentication.getPrincipal();

			Optional<com.portal.backend.clientinterviewtracker.entity.User> user = userRepository.findByEmail(deletedBy.getUsername());
			user.ifPresent(value -> existingSkillReviewTrackerDetails.setLastModifiedByUser(value.getEmail()));

			skillReviewTrackerRepository.save(existingSkillReviewTrackerDetails);
		}catch (Exception e){
			throw new EntityNotFoundException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public String saveIncubationDetails(IncubationNeeded incubationDetails) {
		incubationNeededRepository.save(incubationDetails);
		return "Incubation Details saved successfully";
	}

	@Override
	public List<IncubationNeeded> getAllIncubationDetails() {
		return incubationNeededRepository.findAll();
	}


}
