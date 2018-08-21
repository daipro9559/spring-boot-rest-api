package com.example.demo_mysql.security

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
        @Value( "\${app.jwtSecret}")
        val jwtSecret : String,
        @Value("\${app.jwtExpirationInMs}")
        private val jwtExpirationInMs: Int ) {
    fun generateToken(authencation: Authentication) : String{
        val userPrincipal = (authencation.principal) as UserPrincipal
        val now  = Date()
        val dateExp = Date(now.time + jwtExpirationInMs)
        return Jwts.builder()
                .setSubject(userPrincipal.id.toString())
                .setExpiration(dateExp)
                .compact()
    }

    fun getUserIdFromJwt(token:String) : Long{
        val claim = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body
        return claim.subject.toLong()
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            System.out.print("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            System.out.print("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            System.out.print("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            System.out.print("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            System.out.print("JWT claims string is empty.")

        }

        return false
    }
}