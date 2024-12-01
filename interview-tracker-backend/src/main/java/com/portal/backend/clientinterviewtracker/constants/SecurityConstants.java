package com.portal.backend.clientinterviewtracker.constants;

public class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class -"+getClass().getName());
    }

    public static final String JWT_EXPIRATION_TIME="jwt.expiration.time";
    public static final String JWT_SECRET_KEY="jwt.secret.key";

    public static final String JWT_AUTHORIZATION="Authorization";

    public static final String JWT_BEARER_TOKEN="Bearer ";
}
