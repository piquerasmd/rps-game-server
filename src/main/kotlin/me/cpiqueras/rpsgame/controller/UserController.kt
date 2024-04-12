package me.cpiqueras.rpsgame.controller

import me.cpiqueras.rpsgame.model.User
import me.cpiqueras.rpsgame.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping("/username/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<User> =
        userService.getUserByUsername(username)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}