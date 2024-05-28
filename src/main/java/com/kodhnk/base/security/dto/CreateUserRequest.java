package com.kodhnk.base.security.dto;

import lombok.Builder;

@Builder
public record CreateUserRequest(
        String firstname,
        String lastname,
        String email,
        String username,
        String password
) {
}