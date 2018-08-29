package com.example.demo_mysql.payload

import javax.validation.constraints.NotBlank

class SignupRequest(
        @NotBlank
        val name: String,
        @NotBlank
        val username: String,
        @NotBlank
        val email: String,
        @NotBlank
        val password: String) {
}