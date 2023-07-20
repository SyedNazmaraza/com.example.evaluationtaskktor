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
@Serializable
data class Update(
    var id:Int?=null,
    var title:String?=null,
    var description:String?=null,
    var price:Long?=null,
    var discountPercentage:Double?=null,
    var rating:Double?=null,
    var stock:Int?=null,
    var brand:String?=null,
    var category:String?=null,
    var thumbnail:String?=null,
    var images:List<String>?=null
)
@Serializable
data class Request(
    val query : String?=null
)
