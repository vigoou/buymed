package com.quoctran.pharmacies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.quoctran.pharmacies.data.db.entity.OrderEntity
import com.quoctran.pharmacies.data.db.entity.OrderWithProducts
import com.quoctran.pharmacies.data.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    fun addOrder(orderEntity: OrderEntity) : Long

    @Query("SELECT * FROM `order`")
    fun getListOrder() : Flow<List<OrderEntity>>

    @Transaction
    @Query("SELECT * FROM `Order`")
    fun getOrdersWithProducts(): List<OrderWithProducts>

    @Transaction
    @Query("SELECT * FROM `Order` WHERE id = :orderId")
    fun getOrderWithProductsById(orderId: Long): OrderWithProducts

    @Transaction
    @Query("SELECT * FROM `Order`")
    fun getAllOrdersWithProducts(): List<OrderWithProducts>

    @Transaction
    @Query("SELECT * FROM `Order`")
    fun getAllOrdersWithProductsFlow(): Flow<List<OrderWithProducts>>
}