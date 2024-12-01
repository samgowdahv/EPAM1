package com.portal.backend.clientinterviewtracker.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserAuthDto {

    private String email;
    private String token;
}
