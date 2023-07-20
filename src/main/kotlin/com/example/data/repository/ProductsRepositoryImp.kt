package com.example.data.repository

import com.example.data.DataFactory.dbQuery
import com.example.data.schemas.ProductsTable
import com.example.domain.interfaces.ProductsRepository
import com.example.data.model.Product
import com.example.utils.BaseResponse
import com.example.utils.extensions.toProduct
import org.jetbrains.exposed.sql.*

class ProductsRepositoryImp:ProductsRepository {
    override suspend fun addProduct(product: Product) {
        dbQuery{
            ProductsTable.insert {
                it[id] = product.id
                it[title]= product.title
                it[description]=product.description
                it[price] =product.price
                it[discountPercentage] = product.discountPercentage
                it[rating] = product.rating
                it[stock] = product.stock
                it[brand] = product.brand
                it[category] = product.category
                it[thumbnail] = product.thumbnail
                it[images] = product.images.toString()
            }
        }
    }
    override suspend fun searchProducts(name: String) : BaseResponse<List<Product>>{
        return try {
            dbQuery {
                val query = ProductsTable.selectAll()
                val list = arrayListOf<Product>()
                query.forEach {
                    if(it[ProductsTable.title].contains(name, ignoreCase = true) || it[ProductsTable.description].contains(name)){
                        list.add(it.toProduct())
                    }
                }
                BaseResponse.SuccessResponse<List<Product>>(data = list)
            }

        }
        catch (e:Exception){
            BaseResponse.ErrorResponse(errorCodes = "DATABASE ERROR")

        }

    }

    override suspend fun fetchProducts(name: String): BaseResponse<List<Product>> {
        return try {
            dbQuery {
                val query = ProductsTable.selectAll()
                val list = arrayListOf<Product>()
                query.forEach {
                    if(it[ProductsTable.category].contains(name, ignoreCase = true)){
                        list.add(it.toProduct())
                    }
                }
                BaseResponse.SuccessResponse<List<Product>>(data = list)
            }

        }
        catch (e:Exception){
            BaseResponse.ErrorResponse(errorCodes = "DATABASE ERROR")

        }
    }

    override suspend fun deleteProduct(name: String): BaseResponse<String> {
        return try {
            dbQuery {
                val query = ProductsTable.deleteWhere {
                    ProductsTable.category eq name
                } > 0
                if (query) {
                    BaseResponse.SuccessResponse<String>("Deleted")
                }
                else{
                    BaseResponse.SuccessResponse<String>("NotDeleted")
                }
            }

        }
        catch (e:Exception){
            BaseResponse.ErrorResponse(errorCodes = "DATABASE ERROR")

        }

    }

    override suspend fun updateProduct(newProduct: Product): BaseResponse<List<Product>> {
        return try {
            dbQuery {
                val query = ProductsTable.update({ProductsTable.id eq newProduct.id}){
                    it[title] = title
                    it[description] = newProduct.description
                    it[price] = newProduct.price
                    it[discountPercentage] = newProduct.discountPercentage
                    it[rating] = newProduct.rating
                    it[stock] = newProduct.stock
                    it[brand] = newProduct.brand
                    it[category] = newProduct.category
                    it[thumbnail] = newProduct.thumbnail
                    it[images] = newProduct.images.toString()
                }>0


                if (query){
                    BaseResponse.SuccessResponse<List<Product>>(listOf(newProduct))
                }
                else{
                    BaseResponse.ErrorResponse(errorCodes = "Product Not Present")
                }
            }
        }
        catch (e:Exception){
            BaseResponse.ErrorResponse(errorCodes = "DATABASE ERROR")
        }
    }
}