package com.portal.backend.clientinterviewtracker.service;

import com.portal.backend.clientinterviewtracker.config.JwtConfig.JwtUtil;
import com.portal.backend.clientinterviewtracker.dao.UserRepository;
import com.portal.backend.clientinterviewtracker.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.portal.backend.clientinterviewtracker.constants.UserConstants.USER_EMAIL;
import static com.portal.backend.clientinterviewtracker.constants.UserConstants.USER_TOKEN;

@AllArgsConstructor
@Service
public class GenerateJwtTokenImpl implements GenerateJwtToken {

    private JwtUtil jwtUtil;

    private UserRepository userRepository;


    @Override
    public Map<String,String> createToken(String jwtToken) {

        User user = verifyToken(jwtToken);
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        authoritySet.add(new SimpleGrantedAuthority(user.getRole()));

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authoritySet);

        Map<String,String> userToken = new HashMap<>();
        String token = jwtUtil.generateToken(userDetails);
        userToken.put(USER_EMAIL,user.getEmail());
        userToken.put(USER_TOKEN,token);

        return userToken;
    }

    private User verifyToken(String jwtToken) {

        String claimEmail = null;
        try {
            jwtUtil.extractUsername(jwtToken);
        }
        catch (ExpiredJwtException exception){
            claimEmail = exception.getClaims().getSubject();
        }

        User user = userRepository.findByEmail(claimEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user;
       }
}
