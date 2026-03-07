package com.example.demo.user;

import jakarta.persistence.*;
import lombok.Getter;

public enum  UserStatus {
    ACTIVE,
    INACTIVE,
    BANNED
}
