package com.rkt.demo.serviceImpl;

import com.rkt.demo.entity.RefreshToken;
import com.rkt.demo.entity.UserEntity;
import com.rkt.demo.exception.TokenExpiresException;
import com.rkt.demo.exception.TokenNotValidException;
import com.rkt.demo.repository.RefreshTokenRepository;
import com.rkt.demo.repository.UserRepository;
import com.rkt.demo.security.JwtTokenGenerator;
import com.rkt.demo.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    private static final long REFRESH_TOKEN_EXPIRATION = /*10*60*/40*1000; // valid for 10 hours....

    @Override
    public String createRefreshToken(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        // ===============================================================

        // if refresh token already exist for user then delete it from repository...
        RefreshToken refreshToken = refreshTokenRepository.findByUserEntity(userEntity);

        if(refreshToken != null) {
            refreshTokenRepository.delete(refreshToken);
        }
        // ====================================================
        String randomString = UUID.randomUUID().toString();

        RefreshToken newRefreshToken = RefreshToken.builder()
                .token(randomString)
                .expiry(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION))
                .userEntity(userEntity)
                .build();

        refreshTokenRepository.save(newRefreshToken);

        return randomString;
    }

    @Override
    public String getAccessTokenFromRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token);
        if(refreshToken == null) {
            throw new TokenNotValidException("token is not valid.");
        }

        validateRefreshToken(refreshToken);

        // below code will execute only when token is valid...
        return jwtTokenGenerator.generateToken(refreshToken.getUserEntity().getEmail());
    }


    private void validateRefreshToken(RefreshToken refreshToken) {

        // if refresh token gets expire...
        if(refreshToken.getExpiry().isBefore(Instant.now())) {
            throw new TokenExpiresException("Refresh token has expired. Please login again.");
        }
        return;
    }
}
