package me.cpiqueras.rpsgame.security

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JwtTokenProviderTest {

    private val jwtTokenProvider = JwtTokenProvider()

    @BeforeEach
    fun setup() {
        jwtTokenProvider.init()
    }

    @Test
    fun `generateToken should return a valid JWT`() {
        val username = "username"
        val jwt = jwtTokenProvider.generateToken(username)

        assertTrue(jwt.isNotEmpty())
    }

    @Test
    fun `validateToken should return true when the token is valid`() {
        val username = "username"
        val jwt = jwtTokenProvider.generateToken(username)

        val isValid = jwtTokenProvider.validateToken(jwt)

        assertTrue(isValid)
    }

    @Test
    fun `getUsernameFromJWT should return the correct username`() {
        val username = "username"
        val jwt = jwtTokenProvider.generateToken(username)

        val result = jwtTokenProvider.getUsernameFromJWT(jwt)

        assertEquals(username, result)
    }
}