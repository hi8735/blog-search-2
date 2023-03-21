package com.assessment.exception

open class CustomException(
    open val errorMessage: String = CommonErrorCode.INTERNAL_SERVER_EXCEPTION.message,
    open val errorCode: ErrorCode,
    override val cause: Throwable?
) : RuntimeException()
