package com.rkt.app.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if(response.getStatus() <400){
            System.out.println(response.getStatus());
            System.out.println(response.getHeaderNames());
            System.out.println( authException.getCause() );
            authException.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(authException.getMessage());
        }
//        else if(response.getStatus()==401) {
//            response.getWriter().write("Token is required");
//        }
//        request.

//        System.out.println(authException);
    }
}
