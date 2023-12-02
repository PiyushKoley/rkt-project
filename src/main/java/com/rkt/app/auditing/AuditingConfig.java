package com.rkt.app.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<Long> getAuditorAware() {
        return new ApplicationAuditorAware();
    }
}
