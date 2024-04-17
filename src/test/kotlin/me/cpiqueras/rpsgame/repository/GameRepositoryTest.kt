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

    @Test
    fun `When findByUserId then return List of Games`() {
        val user = User(
            username = "testUser",
            email = "testUser@test.com",
            password = "testPassword",
        )
        val game1 = Game(
            user = user,
            userChoice = Choice.ROCK,
            computerChoice = Choice.PAPER,
            result = Result.LOSE
        )
        val game2 = Game(
            user = user,
            userChoice = Choice.ROCK,
            computerChoice = Choice.PAPER,
            result = Result.LOSE
        )
        entityManager.persist(user)
        entityManager.persist(game1)
        entityManager.persist(game2)
        entityManager.flush()

        val foundGames = gameRepository.findByUserId(user.id)

        assertEquals(2, foundGames.size)
        assertTrue(foundGames.containsAll(listOf(game1, game2)))
    }
}