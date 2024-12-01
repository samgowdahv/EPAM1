package com.portal.backend.clientinterviewtracker.service;

import java.util.List;

import com.portal.backend.clientinterviewtracker.dto.UpdatePasswordDTO;
import com.portal.backend.clientinterviewtracker.dto.UpdateUserDTO;
import com.portal.backend.clientinterviewtracker.dto.UserDTO;
import com.portal.backend.clientinterviewtracker.entity.User;

public interface UserRegistrationService {

	User createUser(UserDTO userDto) throws Exception ;

	void deleteUserByEmail(String email);

	List<User> getAllUsers();

	void updateUserByEmail(String email, UpdateUserDTO updatedUserDto) throws Exception;

	User resetPassword(UpdatePasswordDTO updatePasswordDTO) throws Exception;
	
	void sendForgotPasswordEmail(String email) throws Exception;
}
