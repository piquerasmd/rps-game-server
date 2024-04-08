package me.cpiqueras.rpsgame.repository

import me.cpiqueras.rpsgame.model.Game
import me.cpiqueras.rpsgame.model.User
import me.cpiqueras.rpsgame.model.enums.Choice
import me.cpiqueras.rpsgame.model.enums.Result
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime

@DataJpaTest
class GameRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val gameRepository: GameRepository
) {

    @Test
    fun `When findById then return Game`() {
        val user = User(
            username = "testUser",
            email = "testUser@test.com",
            password = "testPassword",
            createdAt = LocalDateTime.now()
        )
        val game = Game(
            user = user,
            userChoice = Choice.ROCK,
            computerChoice = Choice.PAPER,
            result = Result.LOSE
        )
        entityManager.persist(user)
        entityManager.persist(game)
        entityManager.flush()

        val foundGame = gameRepository.findById(game.id)

        assertTrue(foundGame.isPresent)
        assertEquals(game.result, foundGame.get().result)
    }
}