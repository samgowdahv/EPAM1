package com.portal.backend.clientinterviewtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interview_details")
public class InterviewDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_details_id")
    private Long id;

    @Column(name = "projectCode", length = 255)
    private String projectCode;

    @OneToOne
    @JoinColumn (name = "client_id", referencedColumnName = "client_details_id")
    @ToString.Exclude
    private ClientDetails clientDetails;

    @Column(name = "interviewType", length = 255)
    private String interviewType;

    @Column(name = "associateName", length = 255)
    private String associateName;

    @Column(name = "kbPageLink")
    private String kbPageLink;

    @Lob
    @Column(name="comments", columnDefinition = "text")
    private String comments;

    @Column(name = "updatedKbPage")
    private boolean updatedKbPage;

    @Column(name = "retired")
    private boolean retired;

    @Column(name = "interview_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date interviewDate;

    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name="row_updt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowUpdtDate;

    @Column(name="row_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowEndDate;
}
