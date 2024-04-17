package me.cpiqueras.rpsgame.controller

import io.mockk.every
import io.mockk.mockk
import me.cpiqueras.rpsgame.dto.UserDTO
import me.cpiqueras.rpsgame.model.User
import me.cpiqueras.rpsgame.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class UserControllerTest {

    private val userService = mockk<UserService>()
    private val userController = UserController(userService)

    @Test
    fun `getUserByUsername should return user when user exists`() {
        val username = "username"
        val user = User(1, "username", "email@example.com", "password")
        val expectedUser = UserDTO.fromEntity(user)
        every { userService.getUserByUsername(username) } returns user

        val response = userController.getUserByUsername(username)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == expectedUser)
    }

    @Test
    fun `getUserByUsername should return 404 when user does not exist`() {
        val username = "username"
        every { userService.getUserByUsername(username) } returns null

        val response = userController.getUserByUsername(username)

        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }
}