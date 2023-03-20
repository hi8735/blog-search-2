package com.assessment.presentation

class Response {
    data class Success<T> (val data: T) {
        companion object{
            fun <T> of(data : T) : Success<T> {
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

    data class Error(
        val code: String,
        val name: String,
        val message: String? = ""
    )
}
