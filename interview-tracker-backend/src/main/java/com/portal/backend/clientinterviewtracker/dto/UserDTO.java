package com.portal.backend.clientinterviewtracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	@NotBlank(message = "Name is required")
	@Size(max = 255, message = "Name must be less than or equal to 255 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 255, message = "Email must be less than or equal to 255 characters")
	@Pattern(regexp = ".+@epam\\.com", message = "Email must end with @epam.com")
	private String email;	

	@NotBlank(message = "Role is required")
	@Size(max = 10, message = "Role must be less than or equal to 10 characters")
	private String role;
}
