package com.assessment.exception

data class ValidationException(
    override val errorMessage: String = CommonErrorCode.INVALID_REQUEST.message,
    override val errorCode: ErrorCode = CommonErrorCode.INVALID_REQUEST,
    override val cause: Throwable? = null,
    val errors: List<ErrorDetail> = listOf()
) : CustomException(errorMessage, errorCode, cause)

data class ErrorDetail(
    val field: String,
    val value: String,
    val reason: String
)
