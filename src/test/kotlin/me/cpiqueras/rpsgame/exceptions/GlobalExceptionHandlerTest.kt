package me.cpiqueras.rpsgame.exceptions

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.validation.FieldError
import org.springframework.validation.BindingResult

class GlobalExceptionHandlerTest {

    private val globalExceptionHandler = GlobalExceptionHandler()

    @Test
    fun `handleMethodArgumentNotValidException should return bad request and error message`() {
        val ex = mockk<MethodArgumentNotValidException>()
        val bindingResult = mockk<BindingResult>()
        val fieldError = FieldError("objectName", "field", "defaultMessage")
        every { ex.bindingResult } returns bindingResult
        every { bindingResult.fieldErrors } returns listOf(fieldError)

        val response = globalExceptionHandler.handleMethodArgumentNotValidException(ex)

        assert(response.statusCode == HttpStatus.BAD_REQUEST)
        assert(response.body == "field: defaultMessage")
    }

    @Test
    fun `handleIllegalArgumentException should return bad request and exception message`() {
        val ex = IllegalArgumentException("Illegal argument")

        val response = globalExceptionHandler.handleIllegalArgumentException(ex)

        assert(response.statusCode == HttpStatus.BAD_REQUEST)
        assert(response.body == "Illegal argument")
    }
}