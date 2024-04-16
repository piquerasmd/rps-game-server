package me.cpiqueras.rpsgame.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler{

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val errors = ex.bindingResult
            .fieldErrors
            .map { error -> "${error.field}: ${error.defaultMessage}" }
            .joinToString(", ")

        return ResponseEntity.badRequest().body(errors)
    }

    @ExceptionHandler
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(ex.message)
    }
}