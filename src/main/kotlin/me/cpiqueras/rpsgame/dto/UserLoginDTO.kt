package me.cpiqueras.rpsgame.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UserLoginDTO(
    @field:NotEmpty(message = "Username cannot be empty")
    @field:Size(max = 20, message = "Username must be at most 20 characters")
    val username: String,

    val password: String
)
