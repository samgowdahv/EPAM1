package com.portal.backend.clientinterviewtracker.dto;


import com.portal.backend.clientinterviewtracker.enums.SortType;
import lombok.Data;

@Data
public class InterviewDetailsSearchRequestDto {
    private String sortField;
    private SortType sortType;
    private String searchString;
    private Integer offset;
    private Integer limit;
}