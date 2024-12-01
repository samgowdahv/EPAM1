package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.SkillReviewTrackerDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillReviewTrackerRepository extends JpaRepository<SkillReviewTrackerDetails,Long> {

    Page<SkillReviewTrackerDetails> findAllByRowEndDateIsNullOrderByCreatedDateDesc(Pageable pageable);
    List<SkillReviewTrackerDetails> findAllByRowEndDateIsNullOrderByCreatedDateDesc();

//    @Query(value = "select * into BKP_Skill_Review_Tracker_TBL from Skill_Review_Tracker_TBL",nativeQuery = true)
//    void getAllSkillReviewTracker();

    @Query(value = "select * from BKP_Skill_Review_Tracker_TBL",nativeQuery = true)
    List<SkillReviewTrackerDetails> getAllBkpSkillReviewTracker();


}
