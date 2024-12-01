package com.portal.backend.clientinterviewtracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.backend.clientinterviewtracker.dto.UpdatePasswordDTO;
import com.portal.backend.clientinterviewtracker.dto.UpdateUserDTO;
import com.portal.backend.clientinterviewtracker.dto.UserDTO;
import com.portal.backend.clientinterviewtracker.entity.User;
import com.portal.backend.clientinterviewtracker.service.UserRegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/interview-tracker")
public class UserRegistrationController {

	private final UserRegistrationService userRegistrationService;

	public UserRegistrationController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}

	@Operation(summary = "Create a new user")
	@PostMapping("/v1/users")
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDto) throws Exception {
		User createdUser = userRegistrationService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("User " + createdUser.getName() + " created successfully.");
	}

	@Operation(summary = "Delete a user by email")
	@DeleteMapping("/v1/users/{email}")
	public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
		userRegistrationService.deleteUserByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body("User with email " + email + " deleted successfully.");
	}

	@Operation(summary = "This API will pull all authorized users from database")
	@GetMapping("/v1/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRegistrationService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@Operation(summary = "Update a user by email")
	@PutMapping("/v1/users/{email}")
	public ResponseEntity<String> updateUserByEmail(@PathVariable String email, @Valid @RequestBody UpdateUserDTO updatedUserDto) throws Exception {
		userRegistrationService.updateUserByEmail(email, updatedUserDto);
		return ResponseEntity.status(HttpStatus.OK).body("User with email " + email + " details updated successfully.");
	}

	@PostMapping("/v1/reset-password")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) throws Exception {
		userRegistrationService.resetPassword(updatePasswordDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Password is updated successfully.");
	}

	@PostMapping("/v1/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) throws Exception {
		userRegistrationService.sendForgotPasswordEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body("Password reset email sent successfully.");
	}

}
