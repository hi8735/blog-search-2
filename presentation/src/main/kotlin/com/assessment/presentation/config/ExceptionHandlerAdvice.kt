package com.assessment.presentation.config

import com.assessment.exception.CommonErrorCode
import com.assessment.exception.CustomException
import com.assessment.exception.ValidationException
import com.assessment.presentation.Response
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = ["com.assessment.presentation"])
class ExceptionHandlerAdvice {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): Response.Fail {
        log.error(e.message)

        return Response.Fail.create(CommonErrorCode.INVALID_REQUEST.code, e.javaClass.simpleName, CommonErrorCode.INVALID_REQUEST.message)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException): Response.Fail {
        log.error(e.message, e)

        return Response.Fail.create(CommonErrorCode.INVALID_JSON.code, e.javaClass.simpleName, CommonErrorCode.INVALID_JSON.message)
    }

    @ExceptionHandler(CustomException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun customExceptionHandler(e: CustomException): Response.Fail {
        log.error(e.errorMessage, e)

        return Response.Fail.create(e.errorCode.code, e.javaClass.simpleName, e.errorMessage)
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun validationExceptionHandler(e: ValidationException): Response.ValidationFail {
        log.error(e.message, e)

        return Response.ValidationFail.create(message = e.errorMessage, errors = e.errors)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalExceptionHandler(e: Exception): Response.Fail {
        log.error(e.message, e)

        return Response.Fail.create(CommonErrorCode.INTERNAL_SERVER_EXCEPTION.code, e.javaClass.simpleName, CommonErrorCode.INTERNAL_SERVER_EXCEPTION.message)
    }
}

