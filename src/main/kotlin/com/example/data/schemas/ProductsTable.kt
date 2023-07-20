package com.example.data.schemas

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import java.rmi.Naming.list
import java.util.UUID

object ProductsTable:Table("productss") {

    val id = integer("id").autoIncrement()
    val title = varchar("title", 246)
    val description = varchar("description", 246)
    val price = long("price")
    val discountPercentage = double("discountPercentage")
    val rating = double("rating")
    val stock = integer("stock")
    val brand = varchar("brand", 246)
    val category = varchar("category", 246)
    val thumbnail = varchar("tumbnail", 246)
    val images: Column<String> = text("images").default("[]")
}
