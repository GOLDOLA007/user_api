package com.api.user_api.dto;

import com.api.user_api.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserResponse(int HttpStatus, Long id, String name, String email) {
    public UserResponse(User user) {
        this(200, user.getId(), user.getName(), user.getEmail());
    }
}
