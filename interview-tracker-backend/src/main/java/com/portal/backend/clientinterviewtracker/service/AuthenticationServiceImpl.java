package com.portal.backend.clientinterviewtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.portal.backend.clientinterviewtracker.config.JwtConfig.JwtUtil;
import com.portal.backend.clientinterviewtracker.dto.UserAuthDto;
import com.portal.backend.clientinterviewtracker.dto.UserLoginDto;
import com.portal.backend.clientinterviewtracker.exceptionhandler.InvalidUserException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	public UserAuthDto authenticate(UserLoginDto userLoginDto) {
		String jwtToken = null;
		User user = null;
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
			if (authentication.isAuthenticated()) {
				user = (User) authentication.getPrincipal();
				jwtToken = jwtUtil.generateToken(user);
			}
		} catch (Exception e) {
			throw new InvalidUserException("Temporary password has expired. Please check your email for a updated temporary password.");
		}
		return new UserAuthDto(user.getUsername(), jwtToken);
	}
}
