package com.example.common.result

import com.example.common.result.PhoneNumberResult.Success

sealed class ResultData<out R> {
    data class Success<out T>(val status: T) : ResultData<T>()
    data class Error(val exception: Throwable) : ResultData<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[status=$status]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

enum class ResultStatus {
    SUCCESS,
    FATAL,
    ERROR
}