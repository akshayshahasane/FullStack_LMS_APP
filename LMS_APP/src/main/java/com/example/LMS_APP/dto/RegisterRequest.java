package com.example.LMS_APP.dto;

import com.example.LMS_APP.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}
