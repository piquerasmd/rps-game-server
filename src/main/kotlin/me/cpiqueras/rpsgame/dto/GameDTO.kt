package me.cpiqueras.rpsgame.dto

import me.cpiqueras.rpsgame.model.Game
import me.cpiqueras.rpsgame.model.enums.Choice
import me.cpiqueras.rpsgame.model.enums.Result

data class GameDTO (
    val username: String,
    val userChoice: Choice,
    val computerChoice: Choice,
    val result: Result,
    val playedAt: String
) {
    companion object {
        fun fromEntity(game: Game): GameDTO {
            return GameDTO(
                username = game.user.username,
                userChoice = game.userChoice,
                computerChoice = game.computerChoice,
                result = game.result,
                playedAt = game.playedAt.toString()
            )
        }
    }
}