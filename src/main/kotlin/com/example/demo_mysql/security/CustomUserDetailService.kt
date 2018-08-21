package com.example.demo_mysql.security

import com.example.demo_mysql.exception.ResourceNotfound
import com.example.demo_mysql.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailService (
        @Autowired
        var userRepository : UserRepository

) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(p0: String?): UserDetails {

        val user = userRepository.findUserByEmailOrUsername(p0!!,p0!!)
                .orElseThrow {
                    UsernameNotFoundException("User not found with username or email : " + p0)
                }
        return UserPrincipal.create(user)
    }

    fun loadUserById(id:Long): UserDetails{
        val user = userRepository.findById(id)
                .orElseThrow{ResourceNotfound("User", "id", id)}
        return UserPrincipal.create(user)
    }
}