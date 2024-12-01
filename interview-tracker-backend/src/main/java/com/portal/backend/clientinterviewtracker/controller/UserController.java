package com.portal.backend.clientinterviewtracker.controller;

import static com.portal.backend.clientinterviewtracker.constants.UserConstants.USER_EMAIL;
import static com.portal.backend.clientinterviewtracker.constants.UserConstants.USER_TOKEN;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.backend.clientinterviewtracker.dto.JwtToken;
import com.portal.backend.clientinterviewtracker.dto.UserAuthDto;
import com.portal.backend.clientinterviewtracker.dto.UserLoginDto;
import com.portal.backend.clientinterviewtracker.exception.TokenNotFoundException;
import com.portal.backend.clientinterviewtracker.service.AuthenticationServiceImpl;
import com.portal.backend.clientinterviewtracker.service.GenerateJwtTokenImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/interview-tracker")
public class UserController {

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

	@Autowired
	private GenerateJwtTokenImpl generateJwtTokenImpl;

	@Operation(summary = "Authenticate a User", responses = { @ApiResponse(responseCode = "200", description = "User authenticated and Produces Email and JWT Token"),
			@ApiResponse(responseCode = "403", description = "Forbidden-Bad Credential") })
	@PostMapping("/login")
	public ResponseEntity<UserAuthDto> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) throws Exception {
		return ResponseEntity.ok(authenticationServiceImpl.authenticate(userLoginDto));
	}

	@Operation(summary = "Generate a new JWT Token after expiry", responses = { @ApiResponse(responseCode = "200", description = "User authenticated and Produces Email and JWT Token"),
			@ApiResponse(responseCode = "403", description = "Forbidden-Bad Credential") })
	@PostMapping("/generate-new-token")
	public ResponseEntity<UserAuthDto> generateToken(@RequestBody JwtToken jwtToken) {
		String expiredToken = jwtToken.getToken();
		if (StringUtils.isBlank(expiredToken))
			throw new TokenNotFoundException("Unable to find Expired JWT Token");

		Map<String, String> userToken = generateJwtTokenImpl.createToken(expiredToken);
		UserAuthDto userAuthDto = new UserAuthDto(userToken.get(USER_EMAIL), userToken.get(USER_TOKEN));

		return ResponseEntity.ok(userAuthDto);

	}
}
