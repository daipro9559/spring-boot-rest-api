package com.example.demo_mysql.repository

import com.example.demo_mysql.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User,Long> {
    fun findUserByEmailOrUsername(email:String,user_name:String):Optional<User>
}