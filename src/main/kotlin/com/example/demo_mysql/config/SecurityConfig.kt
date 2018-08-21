package com.example.demo_mysql.config

import com.example.demo_mysql.security.CustomUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var customUserDetailService : CustomUserDetailService

    override fun configure(http: HttpSecurity?) {
        super.configure(http)
        http!!.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic().disable()
    }
}