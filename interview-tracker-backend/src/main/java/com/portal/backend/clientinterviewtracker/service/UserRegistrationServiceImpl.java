package com.portal.backend.clientinterviewtracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.backend.clientinterviewtracker.dao.UserRepository;
import com.portal.backend.clientinterviewtracker.dto.UpdatePasswordDTO;
import com.portal.backend.clientinterviewtracker.dto.UpdateUserDTO;
import com.portal.backend.clientinterviewtracker.dto.UserDTO;
import com.portal.backend.clientinterviewtracker.entity.User;
import com.portal.backend.clientinterviewtracker.exceptionhandler.InvalidUserException;
import com.portal.backend.clientinterviewtracker.util.AESUtil;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private AESUtil aesUtil;
	@Autowired
	EmailService emailService;

	@Value("${jwt.expiration.time:48}")
	private long tokenExpirationHours;

	private List<String> allowedRoles = new ArrayList<>();

	@Autowired
	public void setAllowedRoles(@Value("${allowed.roles}") String roles) {
		if (!roles.isEmpty())
			this.allowedRoles = Arrays.asList(roles.split(","));
	}

	@Override
	@Transactional
	public User createUser(UserDTO userDto) throws Exception {
		if (!allowedRoles.contains(userDto.getRole())) {
			throw new InvalidUserException("Role must be either analyst or admin");
		}
		Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
		if (existingUser.isPresent()) {
			throw new InvalidUserException("Email already exists: " + userDto.getEmail());
		}
		String temporaryPassword = aesUtil.encrypt(UUID.randomUUID().toString());
		User user = User.builder().name(userDto.getName()).email(userDto.getEmail()).tempPasswordExpiry(LocalDateTime.now().plusHours(48)).password(temporaryPassword).role(userDto.getRole())
				.build();
		User savedUser = userRepository.save(user);
		// sending temporary password to user
		sendTemporaryPassword(user, temporaryPassword);
		return savedUser;
	}

	@Override
	@Transactional
	public void deleteUserByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new InvalidUserException("User with email " + email + " does not exist."));
		userRepository.delete(user);
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional
	public void updateUserByEmail(String email, UpdateUserDTO updatedUserDto) throws Exception {
		User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new InvalidUserException("User with email " + email + " does not exist."));

		// Update user details based on updatedUserDto
		existingUser.setName(updatedUserDto.getName());
		existingUser.setPassword(aesUtil.encrypt(updatedUserDto.getPassword()));
		existingUser.setRole(updatedUserDto.getRole());

		userRepository.save(existingUser);
	}

	@Override	
	public User resetPassword(UpdatePasswordDTO updatePasswordDTO) throws Exception {
		User user = userRepository.findByEmail(updatePasswordDTO.getEmail()).orElseThrow(() -> new InvalidUserException("User not found with email: " + updatePasswordDTO.getEmail()));

		// Check if updating with temporary password and it's still valid
		if (user.getTempPasswordExpiry() != null) {
			if (LocalDateTime.now().isAfter(user.getTempPasswordExpiry())) {
				// Temporary password has expired
				String tempPassword = aesUtil.encrypt(UUID.randomUUID().toString());
				user.setPassword(tempPassword);
				user.setTempPasswordExpiry(LocalDateTime.now().plusHours(48));
				user.setModifiedDate(LocalDateTime.now());
				userRepository.save(user);
				sendTemporaryPassword(user, tempPassword);
				throw new InvalidUserException("Temporary password has expired. Please check your email for a updated temporary password.");
			} else if (!StringUtils.equals(updatePasswordDTO.getPassword(), aesUtil.decrypt(user.getPassword())))
				throw new InvalidUserException("Temporary password is incorrect.");
			else {
				user.setPassword(aesUtil.encrypt(updatePasswordDTO.getNewPassword()));
				// Clear the temporary password expiry
				user.setTempPasswordExpiry(null);
				return userRepository.save(user);
			}
		} else {
			if (!StringUtils.equals(aesUtil.encrypt(updatePasswordDTO.getPassword()), user.getPassword()))
				throw new InvalidUserException("Password is not matching with existing password");
			else {
				user.setPassword(aesUtil.encrypt(updatePasswordDTO.getNewPassword()));
				return userRepository.save(user);
			}
		}
	}
	
	@Override
    @Async
    public void sendForgotPasswordEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidUserException("User not found with email: " + email));
        String tempPassword = aesUtil.encrypt(UUID.randomUUID().toString());
		user.setPassword(tempPassword);
		user.setTempPasswordExpiry(LocalDateTime.now().plusHours(48));
		user.setModifiedDate(LocalDateTime.now());
		userRepository.save(user);
        // Send temporary password via email asynchronously
        sendTemporaryPassword(user, tempPassword);
    }

	@Async	
	public void sendTemporaryPassword(User user, String temporaryPassword) throws Exception {		
		emailService.sendSimpleEmail(user.getEmail(), temporaryPassword);
	}

}
