package com.example.demo_mysql.security

import com.example.demo_mysql.model.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors

class UserPrincipal(val id: Long?, val name: String, private val userName: String, @field:JsonIgnore
val email: String, @field:JsonIgnore
                    private val password: String, private val authorities: Collection<GrantedAuthority>) : UserDetails {

    override fun getUsername(): String {
        return userName
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as UserPrincipal?
        return id == that!!.id
    }

    override fun hashCode(): Int {

        return Objects.hash(id)
    }

    companion object {

        fun create(user: User): UserPrincipal {
            val authorities = user.role.stream().map { role -> SimpleGrantedAuthority(role.name.name) }.collect(Collectors.toList())
            return UserPrincipal(
                    user.id,
                    user.name,
                    user.user_name,
                    user.email,
                    user.password,
                    authorities
            )
        }
    }
}
