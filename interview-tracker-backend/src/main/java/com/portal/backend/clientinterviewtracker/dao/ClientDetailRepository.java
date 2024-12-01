package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailRepository extends JpaRepository<ClientDetails, Long> {
}
