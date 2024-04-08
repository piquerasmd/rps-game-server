package me.cpiqueras.rpsgame.controller

import me.cpiqueras.rpsgame.model.Game
import me.cpiqueras.rpsgame.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/games")
class GameController(private val gameService: GameService) {

    @PostMapping
    fun createGame(@RequestBody game: Game): ResponseEntity<Game> =
        ResponseEntity.ok(gameService.saveGame(game))

    @GetMapping("/username/{username}")
    fun getGamesByUsername(@PathVariable username: String): ResponseEntity<List<Game>> =
        ResponseEntity.ok(gameService.getGamesByUsername(username))
}