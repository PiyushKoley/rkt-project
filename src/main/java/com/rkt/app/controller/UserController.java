package com.rkt.app.controller;

import com.rkt.app.dto.requestDto.user.LoginDto;
import com.rkt.app.dto.requestDto.user.RefreshTokenDto;
import com.rkt.app.dto.requestDto.user.UserDto;
import com.rkt.app.dto.responseDto.user.LoginResponseDto;
import com.rkt.app.exception.EmailAlreadyInUserException;
import com.rkt.app.security.CustomUserDetails;
import com.rkt.app.security.JwtTokenGenerator;
import com.rkt.app.service.RefreshTokenService;
import com.rkt.app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    //============================ admin will use these apis ============================

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody()UserDto userDto) throws EmailAlreadyInUserException {

        userService.createUser(userDto);

        return ResponseEntity.ok("user created.");

    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUserNameIdDto(){
        return ResponseEntity.ok(userService.getAllUserNameId());
    }

    //========================================================================================

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


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody()RefreshTokenDto refreshTokenDto) {

        String accessToken = refreshTokenService.getAccessTokenFromRefreshToken(refreshTokenDto.getToken());

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .token(refreshTokenDto.getToken())
                .build();

        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("email" , userDetails.getUsername());
        responseMap.put("role", userDetails.getUserRole());

        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/get-projects")
    public ResponseEntity<?> getAllProjectOfUser() {

        return ResponseEntity.ok(userService.getAllProjectOfUser());
    }
}
