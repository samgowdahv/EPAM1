package com.portal.backend.clientinterviewtracker.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDTO {

	@Size(max = 50, message = "Name cannot exceed 50 characters")
	private String name;

	private String password;

	@Size(max = 10, message = "Role cannot exceed 10 characters")
	private String role;

}
