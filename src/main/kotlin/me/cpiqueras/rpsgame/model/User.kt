package me.cpiqueras.rpsgame.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true)
    val username: String,

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(nullable = false, unique = true)
    val email: String,

    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    var password: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)