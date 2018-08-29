package com.example.demo_mysql.repository

import com.example.demo_mysql.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User,Long> {
    fun findUserByEmailOrUsername(email:String,username:String):Optional<User>
    fun findByEmail(email:String):Optional<User>
    fun findByUsername(username: String) : Optional<User>
    fun existsByUsername(username: String):Boolean
    fun existsByEmail(email:String): Boolean
}