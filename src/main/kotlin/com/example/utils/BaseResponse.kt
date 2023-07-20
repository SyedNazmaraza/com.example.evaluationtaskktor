package com.example.utils

sealed class BaseResponse<out T>() {

    data class SuccessResponse<out T>(
        val data:T
    ):BaseResponse<T>()

    data class ErrorResponse(
        val errorCodes : String
    ):BaseResponse<Nothing>()
}