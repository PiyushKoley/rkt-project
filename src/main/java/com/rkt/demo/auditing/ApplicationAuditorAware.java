package com.rkt.demo.auditing;

import com.rkt.demo.entity.UserEntity;
import com.rkt.demo.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        System.out.println(userDetails.getUserId());
        return Optional.of(userDetails.getUserId());
    }
}
