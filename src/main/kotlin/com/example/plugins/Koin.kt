package com.example.plugins

import com.example.data.repository.ProductsRepositoryImp
import com.example.domain.interfaces.ProductsRepository
import com.example.domain.usecases.ProductsService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.koin

fun Application.configKoin(){
    val module = module {
        single<ProductsRepository>{ProductsRepositoryImp()}
        single { ProductsService(get()) }
    }
    koin {
        modules(module)
    }
}