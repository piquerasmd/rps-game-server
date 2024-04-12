package me.cpiqueras.rpsgame.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtTokenProvider {

    private lateinit var secretKey: SecretKey
    private val jwtExpirationInMs = 3600000 // 1 hour

    @PostConstruct
    fun init() {
        // TODO Move this to a secure location
        secretKey = Keys.hmacShaKeyFor("YourSecretKeyForJWTsNeedsToBeVeryLongAndSecure".toByteArray())
    }

    fun generateToken(username: String): String {

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + jwtExpirationInMs))
            .signWith(secretKey)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).let {
                !it.getPayload().getExpiration().before(Date())
            }
        } catch (e: Exception) {
            false
        }
    }

    fun getUsernameFromJWT(token: String): String {
        val claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)

        return claims.getPayload().getSubject()
    }
}