package me.cpiqueras.rpsgame.dto

import jakarta.validation.constraints.NotEmpty
import me.cpiqueras.rpsgame.model.Game
import me.cpiqueras.rpsgame.model.User
import me.cpiqueras.rpsgame.model.enums.Choice
import me.cpiqueras.rpsgame.model.enums.Result

data class GameCreateDTO(
    @field:NotEmpty(message = "Username cannot be empty")
    val username: String,

    @field:NotEmpty(message = "User choice cannot be empty")
    val userChoice: Choice,

    @field:NotEmpty(message = "Computer choice cannot be empty")
    val computerChoice: Choice,

    @field:NotEmpty(message = "Result cannot be empty")
    val result: Result,
){
    companion object {
        fun toEntity(dto: GameCreateDTO, user: User): Game {
            return Game(
                user = user,
                userChoice = dto.userChoice,
                computerChoice = dto.computerChoice,
                result = dto.result
            )
         }
    }
}
