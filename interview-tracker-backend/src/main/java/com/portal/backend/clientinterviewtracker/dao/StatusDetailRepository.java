package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.StatusDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDetailRepository extends JpaRepository<StatusDetails, Long> {
}
