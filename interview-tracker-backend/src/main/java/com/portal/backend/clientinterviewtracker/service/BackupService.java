package com.portal.backend.clientinterviewtracker.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BackupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createBackupTable() {
        try {
            String sql = "SELECT * INTO BKP_Skill_Review_Tracker_TBL FROM Skill_Review_Tracker_TBL";
            jdbcTemplate.execute(sql);
        }catch (Exception e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public void createEmployeeIdColumn(){
        try {
            String sql = "alter table Skill_Review_Tracker_TBL add employee_id varchar(20)";
            jdbcTemplate.execute(sql);
        }catch (Exception e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
