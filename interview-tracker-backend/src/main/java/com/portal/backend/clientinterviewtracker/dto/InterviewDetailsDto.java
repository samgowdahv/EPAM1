package com.portal.backend.clientinterviewtracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterviewDetailsDto {

    private Long id;
    private String clientName;
    private String projectCode;
    private String interviewType;
    private String associateName;
    private String kbPageLink;
    private String comments;
    private boolean updatedKbPage;
    private boolean retired;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date interviewDate;
}
