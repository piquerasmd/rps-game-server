package me.cpiqueras.rpsgame.service

import me.cpiqueras.rpsgame.dto.UserCreateDTO
import me.cpiqueras.rpsgame.model.User

interface UserService {
    fun saveUser(createUserDto: UserCreateDTO): User
    fun getUserById(id: Long): User?
    fun getUserByUsername(username: String): User?
    fun authenticateUser(username: String, password: String): User?
}