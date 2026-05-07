package com.api.user_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "The Name is required")
        String name,
        @Email(message = "Invalid E-mail address")
        @NotBlank(message = "The E-mail is required")
        String email,
        @Size(min=6, message="The Password must be at least 6 characteres long")
        String password
        ) {}
