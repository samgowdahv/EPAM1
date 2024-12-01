package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.PriorityDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityDetailRepository extends JpaRepository<PriorityDetails,Long> {
}
