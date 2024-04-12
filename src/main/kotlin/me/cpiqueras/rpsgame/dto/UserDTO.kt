package me.cpiqueras.rpsgame.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDTO(
    @field:NotBlank(message = "Username cannot be empty")
    @field:Size(max = 20, message = "Username must be at most 20 characters")
    val username: String,

    @field:NotBlank(message = "Email cannot be empty")
    @field:Email(message = "Invalid email address")
    val email: String
)