package com.portal.backend.clientinterviewtracker.service;

import com.portal.backend.clientinterviewtracker.dto.UserAuthDto;
import com.portal.backend.clientinterviewtracker.dto.UserLoginDto;

public interface AuthenticationService {
    public UserAuthDto authenticate(UserLoginDto userLoginDto);
}

