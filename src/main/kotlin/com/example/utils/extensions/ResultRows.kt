package com.example.utils.extensions

import com.example.data.model.Product
import com.example.data.schemas.ProductsTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toProduct():Product = Product(
        id = this[ProductsTable.id],
        title =this[ProductsTable.title],
        description=this[ProductsTable.description],
        price = this[ProductsTable.price],
        discountPercentage = this[ProductsTable.discountPercentage],
        rating = this[ProductsTable.rating],
        stock = this[ProductsTable.stock],
        brand=this[ProductsTable.brand],
        category = this[ProductsTable.category],
        thumbnail = this[ProductsTable.thumbnail],
        images = listOf(this[ProductsTable.images])
)