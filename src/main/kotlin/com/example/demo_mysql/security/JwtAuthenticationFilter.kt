package com.example.demo_mysql.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    lateinit var jwtTokenProvider: JwtTokenProvider
    @Autowired
    lateinit var customUserDetailService: CustomUserDetailService

    override fun doFilterInternal(p0: HttpServletRequest, p1: HttpServletResponse, p2: FilterChain) {

    }

    private fun getJwtFromRequet(request: HttpServletRequest): String ?{
        val bearToken = request.getHeader("Authorization")
        if (StringUtils.hasText(bearToken) && bearToken.startsWith("Bearer ")){
            return bearToken.substring(7,bearToken.length)
        }
        return  null
    }
}