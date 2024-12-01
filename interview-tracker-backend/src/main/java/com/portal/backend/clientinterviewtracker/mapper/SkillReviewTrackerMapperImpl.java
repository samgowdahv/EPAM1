package com.portal.backend.clientinterviewtracker.mapper;

import com.portal.backend.clientinterviewtracker.dao.IncubationRepository;
import com.portal.backend.clientinterviewtracker.dto.SkillReviewTrackerDto;
import com.portal.backend.clientinterviewtracker.entity.Incubation;
import com.portal.backend.clientinterviewtracker.entity.SkillReviewTrackerDetails;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
@AllArgsConstructor
public class SkillReviewTrackerMapperImpl implements SkillReviewTrackerMapper{

    private IncubationRepository incubationRepository;
    @Override
    public  SkillReviewTrackerDto toDto(SkillReviewTrackerDetails skillReviewTrackerDetails) {

        SkillReviewTrackerDto skillReviewTrackerDto = new SkillReviewTrackerDto();
        if(Objects.nonNull(skillReviewTrackerDetails)){

            skillReviewTrackerDto = SkillReviewTrackerDto.builder()
                    .trackId(skillReviewTrackerDetails.getTrackId())
                    .employeeId(skillReviewTrackerDetails.getEmployeeId())
                    .employeeName(skillReviewTrackerDetails.getEmployeeName())
                    .skillReviewType(skillReviewTrackerDetails.getSkillReviewType())
                    .levels(skillReviewTrackerDetails.getLevels())
                    .priority(skillReviewTrackerDetails.getPriority())
                    .requestDate(skillReviewTrackerDetails.getRequestDate())
                    .status(skillReviewTrackerDetails.getStatus())
                    .completionDate(skillReviewTrackerDetails.getCompletionDate())
                    .rating(skillReviewTrackerDetails.getRating())
                    .action(skillReviewTrackerDetails.getAction())
                    .comments(skillReviewTrackerDetails.getComments())
                    .reviewer(skillReviewTrackerDetails.getReviewer())
                    .plannedDate(skillReviewTrackerDetails.getPlannedDate()).build();

            List<String> employeeIdList = new ArrayList<>();
            employeeIdList.add(skillReviewTrackerDetails.getEmployeeId());
            List<Incubation> incubationList = incubationRepository.findByEmployeeIdIn(employeeIdList);
            StringJoiner joiner = new StringJoiner(",");
            boolean atLeastOneIncubationAdded = false;

            if (CollectionUtils.isEmpty(incubationList)) {
                skillReviewTrackerDto.setIncubation(null);
            } else {
                for(Incubation incubation : incubationList){
                    if(incubation.isIncubationAdded()) {
                        joiner.add(incubation.getIncubationSkill());
                        atLeastOneIncubationAdded = true;
                    }
                }
                if (atLeastOneIncubationAdded) {
                    skillReviewTrackerDto.setIncubation(joiner.toString());
                } else {
                    skillReviewTrackerDto.setIncubation(null);
                }

            }

        }
        return skillReviewTrackerDto;
    }

    @Override
    public List<SkillReviewTrackerDto> getSkillReviewTrackerDetailsDto(Page<SkillReviewTrackerDetails> skillReviewTrackerDetailsPageList){

        List<SkillReviewTrackerDto> skillReviewTrackerDtoList = new ArrayList<>();
     for(SkillReviewTrackerDetails skillReviewTrackerDetails : skillReviewTrackerDetailsPageList){
         SkillReviewTrackerDto skillReviewTrackerDto = toDto(skillReviewTrackerDetails);
         skillReviewTrackerDtoList.add(skillReviewTrackerDto);
     }
     return skillReviewTrackerDtoList;
    }

}
