package com.example.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseResponse<out T>() {

    @Serializable
    data class SuccessResponse<T>(
        val data:T
    ):BaseResponse<T>()
    @Serializable
    data class ErrorResponse(
        val errorCodes : ErrorCodes,
        var mess :String = ""
    ):BaseResponse<Nothing>()
}