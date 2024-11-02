package com.home.trupper.rest.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken (UserDetails userDetails) {

        return getToken(new HashMap<String, Object>(), userDetails);
    }

    private String getToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+(1000*60*12)))
                .signWith(getKey())
                .compact();

    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String getUsernamefromToken(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = getUsernamefromToken(token);
            return username != null && username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }
}
