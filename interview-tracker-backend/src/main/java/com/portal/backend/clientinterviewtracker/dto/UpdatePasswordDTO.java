package com.portal.backend.clientinterviewtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDTO {
	private String email;
	private String password;
	private String newPassword;
}