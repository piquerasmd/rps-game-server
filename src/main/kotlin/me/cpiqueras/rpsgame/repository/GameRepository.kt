package me.cpiqueras.rpsgame.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import me.cpiqueras.rpsgame.model.Game

@Repository
interface GameRepository : JpaRepository<Game, Long> {
    fun findByUserId(userId: Long): List<Game>
    fun findByUserUsername(username: String): List<Game>
}