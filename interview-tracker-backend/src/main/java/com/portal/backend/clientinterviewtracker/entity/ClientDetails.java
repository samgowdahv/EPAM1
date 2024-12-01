package com.portal.backend.clientinterviewtracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_details")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_details_id")
    private Long id;

    @Column(name="clientCode", length = 255)
    private String clientCode;

    @Column(name="clientName", length = 255)
    private String clientName;

    @Column(name = "created_date",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name="row_updt_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowUpdtDate;

    @Column(name="row_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowEndDate;

    @JsonIgnore
    @OneToOne(mappedBy = "clientDetails")
    private InterviewDetails interviewDetails;

}
