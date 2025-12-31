package com.quoctran.pharmacies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quoctran.pharmacies.data.db.entity.OrderEntity
import com.quoctran.pharmacies.data.db.entity.OrderProductEntity
import com.quoctran.pharmacies.data.db.entity.ProductEntity

@Database(
    entities = [
        ProductEntity::class,
        OrderEntity::class,
        OrderProductEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun orderProductDao(): OrderProductDao
}
