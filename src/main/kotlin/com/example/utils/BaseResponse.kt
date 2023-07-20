package com.example.utils

sealed class BaseResponse<out T>() {

    data class SuccessResponse<T>(
        val data:T
    ):BaseResponse<T>()

    data class ErrorResponse(
        val errorCodes : ErrorCodes,
        var mess :String = ""
    ):BaseResponse<Nothing>()
}