package com.example.plugins

import com.example.domain.usecases.ProductsService
import com.example.routes.productRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configRouting() {
    routing {
        val service by inject<ProductsService>()
        productRoute(service)
    }
}
