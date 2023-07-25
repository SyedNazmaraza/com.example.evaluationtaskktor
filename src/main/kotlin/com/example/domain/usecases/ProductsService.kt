package com.example.domain.usecases

import com.example.data.model.Product
import com.example.data.model.Request
import com.example.data.model.Update
import com.example.domain.interfaces.ProductsRepository
import com.example.utils.BaseResponse
import com.example.utils.Products
import com.example.utils.gettingData

class ProductsService(private val repo: ProductsRepository) {

    suspend fun getAllProducts(): BaseResponse<Products> {
        val data = gettingData()
         if (data is BaseResponse.SuccessResponse){
             data.data.products.forEach { repo.addProduct(it) }
             return data
         }
        return data
    }

    suspend fun searchProducts(request: Request): BaseResponse<List<Product>> {
        return repo.searchProducts(request.query?:"")
    }
    suspend fun fetchProducts(request: Request): BaseResponse<List<Product>> {
         return repo.fetchProducts(request.query?:"")
    }

    suspend fun deleteProduct(name:String): BaseResponse<String> {
        return repo.deleteProduct(name)
    }

    suspend fun updateProduct(request:Update): BaseResponse<Product?> {
        return repo.updateProduct(request)
    }

}
