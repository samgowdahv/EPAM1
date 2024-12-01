package com.portal.backend.clientinterviewtracker.mapper;

import com.portal.backend.clientinterviewtracker.dto.SkillReviewTrackerDto;
import com.portal.backend.clientinterviewtracker.entity.SkillReviewTrackerDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SkillReviewTrackerMapper {
    SkillReviewTrackerDto toDto (SkillReviewTrackerDetails skillReviewTrackerDetails);

    List<SkillReviewTrackerDto> getSkillReviewTrackerDetailsDto(Page<SkillReviewTrackerDetails> skillReviewTrackerDetailsPage);
}
