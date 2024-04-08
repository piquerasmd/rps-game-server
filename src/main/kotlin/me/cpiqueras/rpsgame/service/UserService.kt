package me.cpiqueras.rpsgame.service

import me.cpiqueras.rpsgame.model.User

interface UserService {
    fun saveUser(user: User): User
    fun getUserById(id: Long): User?
    fun getUserByUsername(username: String): User?
}