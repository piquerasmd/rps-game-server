package me.cpiqueras.rpsgame.model

import jakarta.persistence.*
import me.cpiqueras.rpsgame.model.enums.Choice
import me.cpiqueras.rpsgame.model.enums.Result
import java.time.LocalDateTime

@Entity
@Table(name = "games")
class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "user_choice")
    val userChoice: Choice,

    @Enumerated(EnumType.STRING)
    @Column(name = "computer_choice")
    val computerChoice: Choice,

    @Enumerated(EnumType.STRING)
    val result: Result,

    @Column(name = "played_at", nullable = false, updatable = false)
    val playedAt: LocalDateTime = LocalDateTime.now()
)
