package com.rkt.demo.controller;

import com.rkt.demo.dto.requestDto.LoginDto;
import com.rkt.demo.dto.requestDto.RefreshTokenDto;
import com.rkt.demo.dto.requestDto.UserDto;
import com.rkt.demo.dto.responseDto.LoginResponseDto;
import com.rkt.demo.exception.EmailAlreadyInUserException;
import com.rkt.demo.security.JwtTokenGenerator;
import com.rkt.demo.service.RefreshTokenService;
import com.rkt.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody()UserDto userDto) throws EmailAlreadyInUserException {

        userService.createUser(userDto);

        return ResponseEntity.ok("user created.");

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody()LoginDto loginDto) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken
                            (
                                    loginDto.getEmail(),
                                    loginDto.getPassword()
                            )
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<> ("either username or password is wrong.", HttpStatusCode.valueOf(401));
        }

        String accessToken = jwtTokenGenerator.generateToken(loginDto.getEmail());
        String refreshToken = refreshTokenService.createRefreshToken(loginDto.getEmail());

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .token(refreshToken)
                .build();

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @GetMapping("/token_valid_till")
    public ResponseEntity<?> tokenValidTill(HttpServletRequest request) {

        Date date = jwtTokenGenerator.getTokenExpiry(request);
        System.out.println(date);
        return ResponseEntity.ok(date.toString());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody()RefreshTokenDto refreshTokenDto) {

        String accessToken = refreshTokenService.getAccessTokenFromRefreshToken(refreshTokenDto.getToken());

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .token(refreshTokenDto.getToken())
                .build();

        return ResponseEntity.ok(loginResponseDto);
    }
}
