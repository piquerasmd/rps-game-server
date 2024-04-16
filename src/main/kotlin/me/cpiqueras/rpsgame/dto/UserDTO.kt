package me.cpiqueras.rpsgame.dto

import me.cpiqueras.rpsgame.model.User

data class UserDTO(
    val username: String,
    val email: String,
    val createdAt: String
) {
    companion object {
        fun fromEntity(user: User): UserDTO {
            return UserDTO(
                username = user.username,
                email = user.email,
                createdAt = user.createdAt.toString()
            )
        }
    }
}