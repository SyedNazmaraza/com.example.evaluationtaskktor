package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val query : String
)
