package com.portal.backend.clientinterviewtracker.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.portal.backend.clientinterviewtracker.constants.SecurityConstants.JWT_EXPIRATION_TIME;
import static com.portal.backend.clientinterviewtracker.constants.SecurityConstants.JWT_SECRET_KEY;

@Service
public class JwtUtil {

    @Autowired
    private Environment environment;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining()));
        return buildToken(extraClaims, user.getUsername());
    }

    private String buildToken(Map<String, Object> extraClaims, String email) {

        Long expirationTime = null;
        Optional<String> expTimeOptional = Optional.of(environment.getProperty(JWT_EXPIRATION_TIME).trim());
        if(expTimeOptional.isPresent()){
            expirationTime = Long.valueOf(expTimeOptional.get());
        }

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime * 60000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
            return Jwts
                    .parser()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    private Key getSignInKey() {
        String secretKey = environment.getProperty(JWT_SECRET_KEY);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
