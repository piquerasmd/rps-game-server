package me.cpiqueras.rpsgame.controller

import me.cpiqueras.rpsgame.dto.GameCreateDTO
import me.cpiqueras.rpsgame.dto.GameDTO
import me.cpiqueras.rpsgame.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/games")
class GameController(private val gameService: GameService) {

    @PostMapping
    fun createGame(@RequestBody game: GameCreateDTO): ResponseEntity<GameDTO> {
        val createdGame = gameService.saveGame(game)
        return ResponseEntity.ok(GameDTO.fromEntity(createdGame))
    }

    @GetMapping("/username/{username}")
    fun getGamesByUsername(@PathVariable username: String): ResponseEntity<List<GameDTO>> =
        ResponseEntity.ok(gameService.getGamesByUsername(username).map { GameDTO.fromEntity(it) })
}