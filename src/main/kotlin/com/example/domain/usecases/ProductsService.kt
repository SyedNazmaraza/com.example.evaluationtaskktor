package com.example.domain.usecases

import com.example.data.model.Product
import com.example.domain.interfaces.ProductsRepository
import com.example.utils.BaseResponse
import com.example.utils.Products
import com.example.utils.gettingData

class ProductsService(val repo: ProductsRepository) {

    suspend fun getAllProducts(): Products {
        gettingData().products.forEach { repo.addProduct(it) }
        return gettingData()
    }

    suspend fun searchProducts(name:String): BaseResponse<List<Product>> {
        return repo.searchProducts(name)
    }
    suspend fun fetchProducts(name:String): BaseResponse<List<Product>> {
        return repo.fetchProducts(name)
    }

    suspend fun deleteProduct(name:String): BaseResponse<String> {
        return repo.deleteProduct(name)
    }

    suspend fun updateProduct(request:Product): BaseResponse<List<Product>> {
        return repo.updateProduct(request)
    }

}