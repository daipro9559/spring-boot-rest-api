package com.example.demo_mysql.controller

import com.example.demo_mysql.model.User
import com.example.demo_mysql.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/"])
class MainController {
    @Autowired
    lateinit var userRepository: UserRepository

    @PostMapping(path = ["/add"])
    fun addUSer(@RequestBody name: String, @RequestBody email: String,@RequestBody password:String ): String {
        val user = User()
        user.username = name
        user.email = email
        user.password = password
        userRepository.save(user)
        return "save completed"
    }

    @GetMapping(path = ["/get"])
    fun getAllUser(): List<User> {
        return userRepository.findAll()
    }

    @GetMapping(path = ["/get/{id}"])
    fun getUserById(@PathVariable(value = "id") id: Long): Optional<User> {
        return userRepository.findById(id)
    }

}