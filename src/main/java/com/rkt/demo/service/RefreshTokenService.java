package com.rkt.demo.service;

public interface RefreshTokenService {

    public String createRefreshToken(String email);

//    public boolean validateRefreshToken(String token);

    String getAccessTokenFromRefreshToken(String token);
}
