package com.portal.backend.clientinterviewtracker.controller;

import com.portal.backend.clientinterviewtracker.dao.SkillReviewTrackerRepository;
import com.portal.backend.clientinterviewtracker.entity.SkillReviewTrackerDetails;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
public class FindAllController {


    private SkillReviewTrackerRepository skillReviewTrackerRepository;

    @GetMapping("/find-all")
    public List<SkillReviewTrackerDetails> findAll(){
        return skillReviewTrackerRepository.findAll();
    }
}
