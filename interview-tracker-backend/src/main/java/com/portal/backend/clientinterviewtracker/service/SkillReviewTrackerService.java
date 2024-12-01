package com.portal.backend.clientinterviewtracker.service;

import com.portal.backend.clientinterviewtracker.dto.SkillReviewTrackerDto;
import com.portal.backend.clientinterviewtracker.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SkillReviewTrackerService {
    List<StatusDetails> getAllStatusDetails();

    List<PriorityDetails> getAllPriorities();

    List<ReviewDetails> getReviews();

    List<LevelDetails> getLevels();

    SkillReviewTrackerDto saveSkillReviewTracker(SkillReviewTrackerDto trackerDetails);

    Page<SkillReviewTrackerDto> readSkillReviewTracker(Pageable pageable);

    SkillReviewTrackerDto updateInterview(Long id, SkillReviewTrackerDto skillReviewTrackerDto);
    
    String saveStatusDetails(StatusDetails statusDetails);
    
    String updateStatusDetails(Long id, StatusDetails statusDetails);
    
    String deleteStatusDetailsById(Long id);
    
    String savePriorityDetails(PriorityDetails priorityDetails);
    
    String updatePrioritiesDetails(Long id, PriorityDetails priorityDetails);
    
    String deletePrioritiesById(Long id);
    
    String saveReviewDetails(ReviewDetails reviewDetails);
    
    String updateReviewDetails(Long id, ReviewDetails reviewDetails);
    
    String deleteReviewDetailsById(Long id);
    
    String saveLevelDetails(LevelDetails reviewDetails);
    
    String updateLevelDetails(Long id, LevelDetails reviewDetails);
    
    String deleteLevelDetailsById(Long id);

    void deleteSkillReviewDetails(Long id);

    String saveIncubationDetails(IncubationNeeded incubationDetails);


    List<IncubationNeeded> getAllIncubationDetails();


}
