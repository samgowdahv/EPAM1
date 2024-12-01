package com.portal.backend.clientinterviewtracker.service;

import java.util.Map;

public interface GenerateJwtToken {

   public Map<String,String> createToken(String jwtToken);
}
