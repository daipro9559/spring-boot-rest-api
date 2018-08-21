package com.example.demo_mysql.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired


    override fun doFilterInternal(p0: HttpServletRequest, p1: HttpServletResponse, p2: FilterChain) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}