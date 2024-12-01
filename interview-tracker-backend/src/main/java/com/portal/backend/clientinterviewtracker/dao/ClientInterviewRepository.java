package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.InterviewDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientInterviewRepository extends JpaRepository<InterviewDetails, Long> {
    @Query("SELECT i FROM InterviewDetails i WHERE " +
            "LOWER(i.clientDetails.clientName) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(i.interviewType) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(i.associateName) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(i.kbPageLink) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(cast(i.comments as text)) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    Page<InterviewDetails> findBySearchCriteria(@Param("searchString") String searchString, Pageable pageable);

    List<InterviewDetails> findByAssociateName(String associateName);

    List<InterviewDetails> findByProjectCode(String projectCode);

    Page<InterviewDetails> findAllByRowEndDateIsNullOrderByCreatedDateDesc(Pageable pageable);
    List<InterviewDetails> findAllByRowEndDateIsNullOrderByCreatedDateDesc();



}
