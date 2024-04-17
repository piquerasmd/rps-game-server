package me.cpiqueras.rpsgame.security

import io.mockk.every
import io.mockk.mockk
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder

class JwtAuthenticationFilterTest {

    private val jwtTokenProvider = mockk<JwtTokenProvider>()
    private val jwtAuthenticationFilter = object : JwtAuthenticationFilter(jwtTokenProvider) {
        fun doFilter(request: HttpServletRequest, response: HttpServletResponse) {
            this.doFilterInternal(request, response, mockk(relaxed = true))
        }
    }

    @Test
    fun `doFilter should set authentication when jwt is valid`() {
        val request = MockHttpServletRequest()
        val response = MockHttpServletResponse()
        val jwt = "validJwt"
        request.addHeader("Authorization", "Bearer $jwt")
        every { jwtTokenProvider.validateToken(jwt) } returns true
        every { jwtTokenProvider.getUsernameFromJWT(jwt) } returns "username"

        jwtAuthenticationFilter.doFilter(request, response)

        val authentication = SecurityContextHolder.getContext().authentication

        assert(authentication != null)
        assert(authentication.name == "username")
    }

    @Test
    fun `doFilter should not set authentication when jwt is invalid`() {
        val request = MockHttpServletRequest()
        val response = MockHttpServletResponse()
        val jwt = "invalidJwt"
        request.addHeader("Authorization", "Bearer $jwt")
        every { jwtTokenProvider.validateToken(jwt) } returns false

        jwtAuthenticationFilter.doFilter(request, response)

        val authentication = SecurityContextHolder.getContext().authentication

        assert(authentication == null)
    }
}