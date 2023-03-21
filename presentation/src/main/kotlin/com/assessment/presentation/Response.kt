package com.assessment.presentation

import com.assessment.exception.CommonErrorCode
import com.assessment.exception.ErrorDetail

class Response {
    data class Success<T>(val data: T) {
        companion object {
            fun <T> of(data: T): Success<T> {
                return Success(data)
            }
        }
    }

    data class Fail(val error: Error) {
        companion object {
            fun create(code: String, exceptionName: String, message: String): Fail {
                return Fail(Error(code, exceptionName, message))
            }
        }
    }

    data class ValidationFail(val error: ValidationError) {
        companion object {
            fun create(message: String, errors: List<ErrorDetail>): ValidationFail {
                return ValidationFail(ValidationError(message = message, details = errors))
            }
        }
    }

    data class Error(
        val code: String,
        val name: String,
        val message: String? = ""
    )

    data class ValidationError(
        val code: String = CommonErrorCode.INVALID_REQUEST.code,
        val name: String = CommonErrorCode.INVALID_REQUEST.name,
        val message: String = "",
        val details: List<ErrorDetail> = listOf()
    )
}
