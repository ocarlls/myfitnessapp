package com.ms.user.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record RegisterRequestDTO (String name, @Valid @Email String email, String password) {
}
