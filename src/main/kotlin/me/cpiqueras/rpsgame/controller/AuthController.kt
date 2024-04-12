package me.cpiqueras.rpsgame.controller

import jakarta.validation.Valid
import me.cpiqueras.rpsgame.dto.UserCreateDTO
import me.cpiqueras.rpsgame.dto.UserLoginDTO
import me.cpiqueras.rpsgame.security.JwtTokenProvider
import me.cpiqueras.rpsgame.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userService: UserService, private val jwtUtil: JwtTokenProvider) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid registrationData: UserCreateDTO): ResponseEntity<String> {
        val user = userService.saveUser(registrationData)
        val token = jwtUtil.generateToken(user.username)
        return ResponseEntity.ok(token)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginData: UserLoginDTO): ResponseEntity<String> {
        val user = userService.authenticateUser(loginData.username, loginData.password)
            ?: return ResponseEntity.status(401).body("Invalid credentials")

        val token = jwtUtil.generateToken(user.username)
        return ResponseEntity.ok(token)
    }
}
