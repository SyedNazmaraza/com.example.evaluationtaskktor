package com.example.utils

import com.example.data.model.Product
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*
import java.lang.Exception

suspend fun gettingData(): BaseResponse<Products> {
    val client = HttpClient {
        install(ContentNegotiation){
            gson()
        }
    }
    return try {
        val response = client.get("https://dummyjson.com/products")
        val products = Gson().fromJson(response.bodyAsText(), Products::class.java)
        return products?.let {
            BaseResponse.SuccessResponse(data = it)
        }?:BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR)
    }
    catch (e:Exception){
        BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR)
    }
}

data class Products(var products : Array<Product>
)
