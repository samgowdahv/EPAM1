package com.portal.backend.clientinterviewtracker.dao;

import com.portal.backend.clientinterviewtracker.entity.Incubation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncubationRepository extends JpaRepository<Incubation,Long> {

    List<Incubation> findByEmployeeIdIn(List<String> employeeId);
    Incubation findByIncubationSkill(String skill);
    List<Incubation> findByEmployeeId(String empId);
    Optional<Incubation> findByEmployeeIdAndIncubationSkill(String empId,String skillName);
}
