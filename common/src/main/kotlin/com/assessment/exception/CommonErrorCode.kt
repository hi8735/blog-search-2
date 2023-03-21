package com.assessment.exception

enum class CommonErrorCode(
    override val code: String, override val message: String
) : ErrorCode {
    INVALID_JSON("B0001", "잘못된 요청입니다."),
    INVALID_REQUEST("B0002", "잘못된 요청값입니다."),
    INTERNAL_SERVER_EXCEPTION("B0003", "서버 오류가 발생했습니다."),
    EXTERNAL_API_ERROR("B0004", "외부 API 호출 오류가 발생했습니다."),
}

interface ErrorCode {
    val code: String
    val message: String
}
