package com.example.demo.auth;

import java.time.LocalDate;

public record AuthRequest (
    String email,
    String password
){
}
