package com.quoctran.pharmacies.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "order_product",
    primaryKeys = ["order_id", "product_id"],
)
data class OrderProductEntity(
    @ColumnInfo(name = "order_id", index = true)
    val orderId: Long,

    @ColumnInfo(name = "product_id", index = true)
    val productId: Long,

    val quantity: Int
)