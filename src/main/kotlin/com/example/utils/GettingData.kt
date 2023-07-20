package com.example.utils

import com.example.data.model.Product
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

suspend fun gettingData(): Products {
    val client = HttpClient {
        install(ContentNegotiation){
            gson()
        }
    }
    val response = client.get("https://dummyjson.com/products")
    return Gson().fromJson(response.bodyAsText(),Products::class.java)
}

data class Products(
    val products : Array<Product>
)