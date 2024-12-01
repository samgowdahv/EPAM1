package com.portal.backend.clientinterviewtracker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.backend.clientinterviewtracker.entity.LevelDetails;

public interface LevelDetailsRepository extends JpaRepository<LevelDetails,Long> {
	
	Optional<LevelDetails> findByLevelId(Long levelId);
}
