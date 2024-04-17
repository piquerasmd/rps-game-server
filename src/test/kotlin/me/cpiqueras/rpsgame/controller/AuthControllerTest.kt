package me.cpiqueras.rpsgame.controller

import io.mockk.every
import io.mockk.mockk
import me.cpiqueras.rpsgame.dto.UserCreateDTO
import me.cpiqueras.rpsgame.dto.UserLoginDTO
import me.cpiqueras.rpsgame.model.User
import me.cpiqueras.rpsgame.security.JwtTokenProvider
import me.cpiqueras.rpsgame.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class AuthControllerTest {

    private val userService = mockk<UserService>()
    private val jwtTokenProvider = mockk<JwtTokenProvider>()
    private val authController = AuthController(userService, jwtTokenProvider)

    @Test
    fun `register should return token when user is created successfully`() {
        val registrationData = UserCreateDTO("username", "email@example.com", "password")
        val expectedToken = "token"
        every { userService.saveUser(registrationData) } returns User(1, "username", "mail", "password")
        every { jwtTokenProvider.generateToken("username") } returns expectedToken

        val response = authController.register(registrationData)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == expectedToken)
    }

    @Test
    fun `login should return token when credentials are valid`() {
        val loginData = UserLoginDTO("username", "password")
        val expectedToken = "token"
        every { userService.authenticateUser("username", "password") } returns User(1, "username", "mail", "password")
        every { jwtTokenProvider.generateToken("username") } returns expectedToken

        val response = authController.login(loginData)

        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == expectedToken)
    }

    @Test
    fun `login should return 401 when credentials are invalid`() {
        val loginData = UserLoginDTO("username", "wrongPassword")
        every { userService.authenticateUser("username", "wrongPassword") } returns null

        val response = authController.login(loginData)

        assert(response.statusCode == HttpStatus.UNAUTHORIZED)
        assert(response.body == "Invalid credentials")
    }
}
