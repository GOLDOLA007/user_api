package com.api.user_api.config;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        return Health.status("ONLINE").build();
    }
}