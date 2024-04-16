package me.cpiqueras.rpsgame.controller

import me.cpiqueras.rpsgame.dto.UserDTO
import me.cpiqueras.rpsgame.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping("/username/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<UserDTO> {
        val user = userService.getUserByUsername(username)
        return if (user != null) {
            ResponseEntity.ok(UserDTO.fromEntity(user))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}