package com.example.data.repository

import com.example.data.DataFactory.dbQuery
import com.example.data.schemas.ProductsTable
import com.example.domain.interfaces.ProductsRepository
import com.example.data.model.Product
import com.example.data.model.Update
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes
import com.example.utils.extensions.toProduct
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

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
            when(e){
                is ExposedSQLException -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
                is NoSuchElementException -> BaseResponse.ErrorResponse(ErrorCodes.UPDATE_ERROR,mess = ErrorCodes.UPDATE_ERROR.mes)
                else -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
            }

        }

    }

    override suspend fun fetchProducts(name: String): BaseResponse<List<Product>> {
        return try {
            dbQuery {
                val query = ProductsTable.selectAll()
                val list = arrayListOf<Product>()
                query.forEach {
                    if(it[ProductsTable.category].equals(name, ignoreCase = true)){
                        list.add(it.toProduct())
                    }
                }
                BaseResponse.SuccessResponse<List<Product>>(data = list)
            }

        }
        catch (e:Exception){
            when(e){
                is ExposedSQLException -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
                is NoSuchElementException -> BaseResponse.ErrorResponse(ErrorCodes.UPDATE_ERROR,mess = ErrorCodes.UPDATE_ERROR.mes)
                else -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
            }

        }
    }

    override suspend fun deleteProduct(name: String): BaseResponse<String> {
        return try {
            val query = dbQuery { ProductsTable.deleteWhere { ProductsTable.category eq name } }>0
            if (query){
                BaseResponse.SuccessResponse<String>(data = "Deleted Successfully")
            }
            else{
                BaseResponse.ErrorResponse(errorCodes = ErrorCodes.DELETE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
            }

        }
        catch (e:Exception){
            when(e){
                is ExposedSQLException -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
                is NoSuchElementException -> BaseResponse.ErrorResponse(ErrorCodes.UPDATE_ERROR,mess = ErrorCodes.UPDATE_ERROR.mes)
                else -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
            }

        }

    }

    override suspend fun updateProduct(newProduct: Update): BaseResponse<Product> {
        return try {
            dbQuery {
                val query = ProductsTable.update({ProductsTable.id eq newProduct.id!!}){
                    if (newProduct.title!=null){ it[title] = newProduct.title!! }
                    if (newProduct.category!=null){ it[category] = newProduct.category!! }
                    if (newProduct.description!=null){ it[description] = newProduct.description!! }
                    if (newProduct.brand!=null){ it[brand] = newProduct.brand!! }
                    if (newProduct.discountPercentage!=null){ it[discountPercentage] = newProduct.discountPercentage!!}
                    if (newProduct.images!=null){ it[images] = newProduct.images!!.toString() }
                    if (newProduct.price!=null){ it[price] = newProduct.price!! }
//                    it[price] = newProduct.price?:ProductsTable.select{ id eq newProduct.id!! }.first().toProduct().price
                    if (newProduct.rating!=null){ it[rating] = newProduct.rating!! }
                    if (newProduct.stock!=null){ it[stock] = newProduct.stock!! }
                    if (newProduct.thumbnail!=null){ it[thumbnail] = newProduct.thumbnail!! }
                }>0
                if (query){
                    dbQuery {
                        val product = ProductsTable.select(ProductsTable.id eq newProduct.id!!).first().toProduct()
                        BaseResponse.SuccessResponse<Product>(product)
                    }
                }
                else{
                    BaseResponse.ErrorResponse(errorCodes = ErrorCodes.UPDATE_ERROR, mess = ErrorCodes.UPDATE_ERROR.mes)
                }
            }
        }
        catch (e:Exception){
            when(e){
                is ExposedSQLException -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
                is NoSuchElementException -> BaseResponse.ErrorResponse(ErrorCodes.UPDATE_ERROR,mess = ErrorCodes.UPDATE_ERROR.mes)
                else -> BaseResponse.ErrorResponse(ErrorCodes.DATABASE_ERROR,mess = ErrorCodes.DATABASE_ERROR.mes)
            }

        }
    }
}