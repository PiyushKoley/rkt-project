package com.rkt.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenGenerator {

    public String generateToken(String email) {

        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith( getKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public Date getTokenExpiry( HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        Claims claims;
//        try {
            claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
//        }
//        catch(ExpiredJwtException exception){
//            DecodedJWT decodedJWT = checkExpiredAccessToken(token);
//            return decodedJWT.getExpiresAt();
//        }
        return claims.getExpiration();

    }
    private String getTokenFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    private Key getKey() {
        byte[] bytes = SecurityConstants.JWT_SECRET.getBytes();
        return Keys.hmacShaKeyFor(bytes);
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        }
//        catch(ExpiredJwtException exception){
//            checkExpiredAccessToken(token);
//            return true;
//        }
        catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims ;

//        try {
            claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
//        }
//        catch(ExpiredJwtException exception){
//            DecodedJWT decodedJWT = checkExpiredAccessToken(token);
//            return decodedJWT.getSubject();
//        }

        return claims.getSubject();
    }

//    private DecodedJWT checkExpiredAccessToken(String token) {
//
//        DecodedJWT decodedJWT = JWT.decode(token);
//
//        Date expiredAt = decodedJWT.getExpiresAt();
//
//        System.out.println("**** THIS IS EXPIRED TOKEN **** => " + expiredAt);
//
//        long currentTime = Instant.now().toEpochMilli();
//        long expiredTime = expiredAt.getTime();
//
//        // with in two hours after expiration we can use that expired token also...
//        long shouldNotBeMore = (long)2*60*60*1000;
//
//        // if expired time is more than two hours then throw exception .
//        // and it will get handled by AuthenticationEntryPoint.
//        // and user have to use refresh token in that case...
//        if((currentTime - expiredTime) > shouldNotBeMore) {
//            throw new RuntimeException("token expired. Login again.");
//        }
//
//        return decodedJWT;
//    }
}
