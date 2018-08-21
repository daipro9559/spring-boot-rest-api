package com.example.demo_mysql.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationEntryPoint : AuthenticationEntryPoint{
    override fun commence(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: AuthenticationException?) {
        p1!!.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Sorry, You're not authorized to access this resource.")
    }
}