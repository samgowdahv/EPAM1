package com.portal.backend.clientinterviewtracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EncodedModelDto {
	@NotBlank(message = "Encoded string must not be blank")
	private String encodedString;
}
