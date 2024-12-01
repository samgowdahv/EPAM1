package com.portal.backend.clientinterviewtracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillReviewTrackerDto {

    private Long trackId;
    private String employeeId;
    private String employeeName;
    private String skillReviewType;
    private String levels;
    private String priority;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date requestDate;

    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date completionDate;

    private String rating;
    private String action;
    private String comments;
    private String reviewer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date plannedDate;

    private String incubation;

}
