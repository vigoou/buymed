package com.quoctran.pharmacies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.quoctran.pharmacies.data.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    fun addProduct(productEntity: ProductEntity) : Long

    @Query("SELECT * FROM product")
    fun getListProduct() : List<ProductEntity>
}