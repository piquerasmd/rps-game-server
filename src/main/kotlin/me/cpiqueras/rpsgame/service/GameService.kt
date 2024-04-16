package me.cpiqueras.rpsgame.service

import me.cpiqueras.rpsgame.dto.GameCreateDTO
import me.cpiqueras.rpsgame.model.Game

interface GameService {
    fun saveGame(game: GameCreateDTO): Game
    fun getGameById(id: Long): Game?
    fun getGamesByUserId(userId: Long): List<Game>
    fun getGamesByUsername(username: String): List<Game>
}