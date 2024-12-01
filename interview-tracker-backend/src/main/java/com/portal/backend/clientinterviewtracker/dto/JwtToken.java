package com.portal.backend.clientinterviewtracker.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtToken {

    @NonNull
    private String token;
}
