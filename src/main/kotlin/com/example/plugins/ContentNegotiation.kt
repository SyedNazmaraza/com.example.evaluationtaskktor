package com.example.plugins

import com.example.data.model.Update
import io.ktor.serialization.gson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json


fun Application.configContentNegotiation(){
    install(ContentNegotiation){
        gson {
            clearIgnoredTypes()
        }

    }
}