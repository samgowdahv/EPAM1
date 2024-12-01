package com.portal.backend.clientinterviewtracker.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    private String email;

    private String password;
}

