package com.example.common.result

sealed class PhoneNumberResult<out R> {

    data class Success<out T>(val data: T) : PhoneNumberResult<T>()
    data class Error(val exception: Throwable) : PhoneNumberResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[status=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

enum class PhoneNumberStatus(val status: String, val message: String) {
    SUCCESS("status", "SMS-код отправлен на указанный номер телефона."),
    ERROR("error", "Ошибка отправки SMS-кода на указанный номер.")
}