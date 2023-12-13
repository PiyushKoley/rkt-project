package com.rkt.app.security;

import com.rkt.app.exception.TokenExpiresException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
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


    private Key getKey() {
        byte[] bytes = SecurityConstants.JWT_SECRET.getBytes();
        return Keys.hmacShaKeyFor(bytes);
    }
    public boolean validateToken(String token, HttpServletResponse response) throws IOException {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        }
        catch(ExpiredJwtException exception){
//            checkExpiredAccessToken(token);
//            return true;
            response.getWriter().write("token is expired plz check");
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            System.out.println("inside############");
            return false;
        }
        catch (Exception e) {
            response.getWriter().write("token is tempered");
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            System.out.println("tempered############");
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

}
