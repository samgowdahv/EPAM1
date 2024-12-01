package com.portal.backend.clientinterviewtracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "incubation_skill_tracker_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Incubation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name="incubation_skill")
    private String incubationSkill;

    @Column(name = "isIncubationNeeded")
    private boolean isIncubationAdded;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    @ToString.Exclude
    private SkillReviewTrackerDetails skillReviewTrackerDetails;

}
