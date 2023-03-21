package com.assessment.presentation.config

import com.assessment.presentation.Response
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlerAdvice {

    private val log = LoggerFactory.getLogger(javaClass)

    //validation 어노테이션 등 요청값에 대한 조건이 맞지 않을 때
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException) : Response.Fail {
        log.error(e.message)

        return Response.Fail.create(CommonErrorCode.INVALID_REQUEST.code, e.javaClass.simpleName, CommonErrorCode.INVALID_REQUEST.message)
    }

    //요청에 대한 json 파싱 문제
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException) : Response.Fail {
        log.error(e.message, e)

        return Response.Fail.create(CommonErrorCode.INVALID_JSON.code, e.javaClass.simpleName, CommonErrorCode.INVALID_JSON.message)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalExceptionHandler(e: Exception) : Response.Fail {
        log.error(e.message, e)

        return Response.Fail.create(CommonErrorCode.INTERNAL_EXCEPTION.code, e.javaClass.simpleName, CommonErrorCode.INTERNAL_EXCEPTION.message)
    }
}

enum class CommonErrorCode(
    override val code: String, override val message: String
) : ErrorCode {
    INVALID_JSON("B0001", "잘못된 요청입니다."),
    INVALID_REQUEST("B0002", "잘못된 요청값입니다."),
    INTERNAL_EXCEPTION("B0003", "서버 오류가 발생했습니다."),
}

interface ErrorCode {
    val code: String
    val message: String
}

