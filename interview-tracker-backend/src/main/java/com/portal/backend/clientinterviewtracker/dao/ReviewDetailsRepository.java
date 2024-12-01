package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.ReviewDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDetailsRepository extends JpaRepository<ReviewDetails,Long> {
}
