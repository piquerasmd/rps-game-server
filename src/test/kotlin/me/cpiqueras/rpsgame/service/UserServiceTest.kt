package me.cpiqueras.rpsgame.service

import io.mockk.mockk
import io.mockk.verify
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import me.cpiqueras.rpsgame.dto.UserCreateDTO
import me.cpiqueras.rpsgame.model.User
import org.junit.jupiter.api.Test
import me.cpiqueras.rpsgame.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder

class UserServiceTest {
    private val userRepository: UserRepository = mockk()
    private val passwordEncoder: PasswordEncoder = mockk()
    private val userService = UserServiceImpl(userRepository, passwordEncoder)

    @Test
    fun `saveUser should save a user`() {
        // Arrange
        val encodedPassword = "encodedpassword"
        val userDto = UserCreateDTO("testuser", "mail", "testpassword")
        val user = User(1, "testuser", "mail", encodedPassword)

        every { passwordEncoder.encode(userDto.password) } returns encodedPassword
        every { userRepository.save(any<User>()) } returns user

        // Act
        val result = userService.saveUser(userDto)

        // Assert
        verify { userRepository.save(any<User>()) }
        verify { passwordEncoder.encode(userDto.password) }
        assertEquals(user.username, result.username)
        assertEquals(encodedPassword, result.password)
    }

    @Test
    fun `getUserById should return a user`() {
        val user = User(1, "testuser", "mail", "encodedpassword")

        every { userRepository.findById(1) } returns java.util.Optional.of(user)

        val result = userService.getUserById(1)

        verify { userRepository.findById(1) }
        assertEquals(user, result)
    }


    @Test
    fun `getUserByUsername should return a user`() {
        val user = User(1, "testuser", "mail", "encodedpassword")

        every { userRepository.findByUsername("testuser") } returns user

        val result = userService.getUserByUsername("testuser")

        verify { userRepository.findByUsername("testuser") }
        assertEquals(user, result)
    }

    @Test
    fun `authenticateUser should return a user when credentials are valid`() {
        val user = User(1, "testuser", "mail", "encodedpassword")

        every { userRepository.findByUsername("testuser") } returns user
        every { passwordEncoder.matches("testpassword", user.password) } returns true

        val result = userService.authenticateUser("testuser", "testpassword")

        verify { userRepository.findByUsername("testuser") }
        verify { passwordEncoder.matches("testpassword", user.password) }
        assertEquals(user, result)
    }

    @Test
    fun `authenticateUser should return null when credentials are invalid`() {
        every { userRepository.findByUsername("testuser") } returns null

        val result = userService.authenticateUser("testuser", "wrongpassword")

        verify { userRepository.findByUsername("testuser") }
        assertNull(result)
    }
}