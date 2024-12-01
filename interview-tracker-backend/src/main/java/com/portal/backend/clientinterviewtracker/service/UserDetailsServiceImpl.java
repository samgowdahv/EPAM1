package com.portal.backend.clientinterviewtracker.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portal.backend.clientinterviewtracker.dao.UserRepository;
import com.portal.backend.clientinterviewtracker.entity.User;
import com.portal.backend.clientinterviewtracker.exceptionhandler.InvalidUserException;
import com.portal.backend.clientinterviewtracker.util.AESUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    public UserRegistrationServiceImpl userRegistrationServiceImpl;
    
    @Autowired
	private AESUtil aesUtil;

    @Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> existingUserOpt = userRepository.findByEmail(email);
		User existingUser = existingUserOpt.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		if (existingUser.getTempPasswordExpiry() != null && LocalDateTime.now().isAfter(existingUser.getTempPasswordExpiry())) {
			try {
				// Temporary password has expired
				String tempPassword = aesUtil.encrypt(UUID.randomUUID().toString());
				existingUser.setPassword(tempPassword);
				existingUser.setTempPasswordExpiry(LocalDateTime.now().plusHours(48));
				existingUser.setModifiedDate(LocalDateTime.now());
				userRepository.save(existingUser);
				userRegistrationServiceImpl.sendTemporaryPassword(existingUser, tempPassword);
				throw new InvalidUserException("Temporary password has expired. Please check your email for a updated temporary password.");
			} catch (Exception e) {
				throw new InvalidUserException("Temporary password has expired. Please check your email for a updated temporary password.");
			}
		}
		Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
		authoritySet.add(new SimpleGrantedAuthority(existingUser.getRole()));

		return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), authoritySet);
	}
}
