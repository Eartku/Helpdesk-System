package com.example.demo.auth;

import java.time.LocalDate;

public record RegisterRequest(
        String fullname,
        LocalDate dob,
        String email,
        String password
) {
}
