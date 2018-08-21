package com.example.demo_mysql.repository

import com.example.demo_mysql.model.Role
import com.example.demo_mysql.model.RoleName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<Role,Long> {
    fun findByName(roleName: RoleName):Optional<Role>
}