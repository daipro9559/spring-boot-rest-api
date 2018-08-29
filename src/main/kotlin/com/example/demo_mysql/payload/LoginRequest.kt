package com.example.demo_mysql.payload

import javax.validation.constraints.NotBlank

data class LoginRequest(
        @NotBlank
        val username: String,
        @NotBlank
        val password: String)