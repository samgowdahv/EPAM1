package com.portal.backend.clientinterviewtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Skill_Review_Tracker_TBL")
public class SkillReviewTrackerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Long trackId;

    @Column(name="employee_name")
    private String employeeName;

    @Column(name="employee_id", nullable = false,unique = true)
    private String employeeId;

    @Column(name="skill_review_type")
    private String skillReviewType;

    @Column(name="levels")
    private String levels;

    @Column(name="priority")
    private String priority;

    @Column(name = "request_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;

    @Column(name = "status")
    private String status;

    @Column(name = "completion_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    @Column(name="rating")
    private String rating;

    @Column(name="action")
    private String action;

    @Column(name="comments") // Ensure the correct column definition
    private String comments;

    @Column(name="reviewer")
    private String reviewer;

    @Column(name="created_date",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name="planned_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plannedDate;

    @Column(name="row_updt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowUpdtDate;

    @Column(name="row_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowEndDate;

    @OneToMany( mappedBy = "skillReviewTrackerDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Incubation> incubationList;

    @Column(name="created_user")
    private String createdByUser;

    @Column(name="updated_user")
    private String lastModifiedByUser;

}
