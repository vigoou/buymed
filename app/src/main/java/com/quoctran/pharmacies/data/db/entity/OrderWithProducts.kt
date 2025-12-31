package com.quoctran.pharmacies.data.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class OrderWithProducts(
    @Embedded val orderEntity: OrderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            OrderProductEntity::class,
            parentColumn = "order_id",
            entityColumn = "product_id"
        )
    )
    val products: List<ProductEntity>
)