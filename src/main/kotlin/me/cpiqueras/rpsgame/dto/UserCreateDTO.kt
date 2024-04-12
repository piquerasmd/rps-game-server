package me.cpiqueras.rpsgame.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import me.cpiqueras.rpsgame.model.User

data class UserCreateDTO(
    @field:NotEmpty(message = "Username cannot be empty")
    @field:Size(max = 20, message = "Username must be at most 20 characters")
    val username: String,

    @field:Email(message = "Invalid email address")
    @field:NotEmpty(message = "Email cannot be empty")
    @field:Size(max = 50, message = "Email must be at most 50 characters")
    val email: String,

    @field:Size(min = 8, message = "Password must be at least 8 characters")
    val password: String
){
    companion object {
        fun toEntity(dto: UserCreateDTO): User {
            return User(
                username = dto.username,
                email = dto.email,
                password = dto.password)
        }
    }
}
