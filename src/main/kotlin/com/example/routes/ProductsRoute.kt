package com.example.routes

import com.example.data.model.Product
import com.example.data.model.Request
import com.example.data.model.Update
import com.example.domain.usecases.ProductsService
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import io.ktor.server.routing.*

fun Route.productRoute(service:ProductsService){
    route("/products"){
        get {
            val response = service.getAllProducts()
            call.respond(response)
        }
        post ("/search"){
            val request = call.receive<Request>()
            val response = service.searchProducts(request)
            call.respond(response)

        }
        post ("/fetch"){
            val request = call.receive<Request>()
            val response = service.fetchProducts(request)
            call.respond(response)

        }
        put {
            val request = call.receive<Update>()
            call.respond(service.updateProduct(request))

        }
        delete {
            val request = call.receive<Request>()
            call.respond(service.deleteProduct(request.query?:""))
        }
    }
}