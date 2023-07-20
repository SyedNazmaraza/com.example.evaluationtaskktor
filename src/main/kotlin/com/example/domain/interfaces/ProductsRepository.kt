package com.example.domain.interfaces

import com.example.data.model.Product
import com.example.data.model.Update
import com.example.utils.BaseResponse

interface ProductsRepository {

    suspend fun addProduct(product: Product)

    suspend fun searchProducts(name:String): BaseResponse<List<Product>>

    suspend fun fetchProducts(name:String): BaseResponse<List<Product>>

    suspend fun deleteProduct(name:String): BaseResponse<String>

    suspend fun updateProduct(newProduct:Update): BaseResponse<Product?>


}
