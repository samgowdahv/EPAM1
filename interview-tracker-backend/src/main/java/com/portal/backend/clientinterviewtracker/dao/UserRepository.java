package com.portal.backend.clientinterviewtracker.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portal.backend.clientinterviewtracker.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	Optional<User> findByEmail(String email);
}
