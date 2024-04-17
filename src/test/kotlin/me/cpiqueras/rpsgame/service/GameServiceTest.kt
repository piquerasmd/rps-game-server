package me.cpiqueras.rpsgame.service

import io.mockk.mockk
import io.mockk.verify
import io.mockk.every
import me.cpiqueras.rpsgame.dto.GameCreateDTO
import me.cpiqueras.rpsgame.model.Game
import me.cpiqueras.rpsgame.model.User
import me.cpiqueras.rpsgame.model.enums.Choice
import me.cpiqueras.rpsgame.model.enums.Result
import me.cpiqueras.rpsgame.repository.GameRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameServiceTest {
    private val gameRepository: GameRepository = mockk()
    private val userService: UserService = mockk()
    private val gameService = GameServiceImpl(gameRepository, userService)

    @Test
    fun `saveGame should save a game`() {
        val gameDto = GameCreateDTO("testuser", Choice.ROCK, Choice.ROCK, Result.DRAW)
        val user = User(1, "testuser", "mail", "encodedpassword")
        val game = Game(1, user, Choice.ROCK, Choice.ROCK, Result.DRAW)

        every { userService.getUserByUsername(gameDto.username) } returns user
        every { gameRepository.save(any<Game>()) } returns game

        val result = gameService.saveGame(gameDto)

        verify { gameRepository.save(any<Game>()) }
        verify { userService.getUserByUsername(gameDto.username) }
        assertEquals(game.result, result.result)
        assertEquals(game.user, result.user)
    }

    @Test
    fun `getGameById should return a game`() {
        val user = User(1, "testuser", "mail", "encodedpassword")
        val game = Game(1, user, Choice.ROCK, Choice.ROCK, Result.DRAW)

        every { gameRepository.findById(1) } returns java.util.Optional.of(game)

        val result = gameService.getGameById(1)

        verify { gameRepository.findById(1) }
        assertEquals(game, result)
    }

    @Test
    fun `getGamesByUserId should return a list of games`() {
        val user = User(1, "testuser", "mail", "encodedpassword")
        val game1 = Game(1, user, Choice.ROCK, Choice.ROCK, Result.DRAW)
        val game2 = Game(2, user, Choice.ROCK, Choice.PAPER, Result.WIN)
        val games = listOf(game1, game2)

        every { gameRepository.findByUserId(1) } returns games

        val result = gameService.getGamesByUserId(1)

        verify { gameRepository.findByUserId(1) }
        assertEquals(games, result)
    }

    @Test
    fun `getGamesByUsername should return a list of games`() {
        val user = User(1, "testuser", "mail", "encodedpassword")
        val game1 = Game(1, user, Choice.ROCK, Choice.ROCK, Result.DRAW)
        val game2 = Game(2, user, Choice.ROCK, Choice.PAPER, Result.WIN)
        val games = listOf(game1, game2)

        every { gameRepository.findByUserUsername("testuser") } returns games

        val result = gameService.getGamesByUsername("testuser")

        verify { gameRepository.findByUserUsername("testuser") }
        assertEquals(games, result)
    }
}