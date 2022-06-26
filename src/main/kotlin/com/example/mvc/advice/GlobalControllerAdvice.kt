package com.example.mvc.advice

import com.example.mvc.controller.exception.ExceptionApiController
import com.example.mvc.controller.put.PutApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// RestController 전역에 대해서 설정한다.
//@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class]) // 해당 익셉션이 터졌을때 여기를 타게 된다.
    fun exception(e: RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}