package me.cpiqueras.rpsgame.service

import me.cpiqueras.rpsgame.model.Game
import me.cpiqueras.rpsgame.repository.GameRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameServiceImpl(private val gameRepository: GameRepository) : GameService {
    @Transactional
    override fun saveGame(game: Game): Game = gameRepository.save(game)

    @Transactional(readOnly = true)
    override fun getGameById(id: Long): Game? = gameRepository.findById(id).orElse(null)

    @Transactional(readOnly = true)
    override fun getGamesByUserId(userId: Long): List<Game> = gameRepository.findByUserId(userId)

    @Transactional(readOnly = true)
    override fun getGamesByUsername(username: String): List<Game> = gameRepository.findByUserUsername(username)
}