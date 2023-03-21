package com.assessment.exception

data class ExternalApiException(
    override val errorMessage: String = CommonErrorCode.EXTERNAL_API_ERROR.message,
    override val errorCode: ErrorCode = CommonErrorCode.EXTERNAL_API_ERROR,
    override val cause: Throwable? = null
) : CustomException(errorMessage, errorCode, cause)
