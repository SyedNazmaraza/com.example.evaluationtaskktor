package com.example

import com.example.data.DataFactory
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DataFactory.init()
    configKoin()
    configContentNegotiation()
    configRequestValidation()
    configStatusPage()
    configRouting()
}
