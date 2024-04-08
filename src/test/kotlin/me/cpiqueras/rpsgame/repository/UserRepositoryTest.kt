package me.cpiqueras.rpsgame.repository

import me.cpiqueras.rpsgame.model.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository
) {

    @Test
    fun `When findById then return User`() {
        val user = User(
            username = "testUser",
            email = "testUser@test.com",
            password = "testPassword",
            createdAt = LocalDateTime.now()
        )
        entityManager.persist(user)
        entityManager.flush()

        val foundUser = userRepository.findById(user.id)

        assertTrue(foundUser.isPresent)
        assertEquals(user.username, foundUser.get().username)
        assertEquals(user.email, foundUser.get().email)
    }

    @Test
    fun `When findByEmail then return User`() {
        val user = User(
            username = "testUser",
            email = "testUser@test.com",
            password = "testPassword",
        )
        entityManager.persist(user)
        entityManager.flush()

        val foundUser = userRepository.findByEmail(user.email)

        assertNotNull(foundUser)
        assertEquals(user.username, foundUser?.username)
        assertEquals(user.email, foundUser?.email)
    }
}