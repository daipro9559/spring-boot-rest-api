package com.example.demo_mysql.controller

import com.example.demo_mysql.model.RoleName
import com.example.demo_mysql.model.User
import com.example.demo_mysql.payload.ApiResponse
import com.example.demo_mysql.payload.JwtAuthenticationResponse
import com.example.demo_mysql.payload.LoginRequest
import com.example.demo_mysql.payload.SignupRequest
import com.example.demo_mysql.repository.RoleRepository
import com.example.demo_mysql.repository.UserRepository
import com.example.demo_mysql.security.JwtAuthenticationEntryPoint
import com.example.demo_mysql.security.JwtTokenProvider
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.configurers.ServletApiConfigurer
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("api/auth")
class AuthController {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<out Any> {
        val authencation = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password))
        SecurityContextHolder.getContext().authentication = authencation
        val jwt = jwtTokenProvider.generateToken(authencation)
        return ResponseEntity.ok(JwtAuthenticationResponse(jwt))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signupRequest: SignupRequest): ResponseEntity<out Any> {
        if (userRepository.existsByUsername(signupRequest.username)) {
            return ResponseEntity(ApiResponse(false, "Username is exist!"), HttpStatus.BAD_REQUEST)
        }

        if (userRepository.existsByEmail(signupRequest.email)) {
            return ResponseEntity(ApiResponse(false, "email is exist!"), HttpStatus.BAD_REQUEST)
        }

        val user = User(signupRequest.name, signupRequest.username, signupRequest.email, signupRequest.password)
        user.password = passwordEncoder.encode(user.password)
        val userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow {
                    Exception("Role not found!")
                }
        user.role = Collections.singleton(userRole)
        val result = userRepository.save(user)
        val location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(result.username).toUri()
        return ResponseEntity.created(location).body(ApiResponse(true,"User registered successfully"))
    }
}