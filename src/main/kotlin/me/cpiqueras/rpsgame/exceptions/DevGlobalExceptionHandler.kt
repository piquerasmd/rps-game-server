package me.cpiqueras.rpsgame.exceptions

import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@Profile("dev")
@ControllerAdvice
class DevGlobalExceptionHandler {

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.status(500).body(ex.message)
    }
}