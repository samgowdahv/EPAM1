package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.Incubation;
import com.portal.backend.clientinterviewtracker.entity.IncubationNeeded;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncubationNeededRepository extends JpaRepository<IncubationNeeded,Long> {

    List<IncubationNeeded> findByNameIn(List<String> names);
    IncubationNeeded findByName(String name);

}
