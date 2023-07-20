package com.example.data.model

import kotlinx.serialization.Serializable
import java.util.UUID


@Serializable
data class Product(
    var id:Int,
    var title:String,
    var description:String,
    var price:Long,
    var discountPercentage:Double,
    var rating:Double,
    var stock:Int,
    var brand:String,
    var category:String,
    var thumbnail:String,
    var images:List<String>
)
