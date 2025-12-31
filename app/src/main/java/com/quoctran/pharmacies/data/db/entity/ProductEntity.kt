package com.quoctran.pharmacies.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.quoctran.pharmacies.domain.model.Product

@Entity(
    tableName = "product"
)
data class ProductEntity(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "price")
    var price: Long,

    @ColumnInfo(name = "code")
    var code: String,

    @ColumnInfo(name = "img_url")
    var imgUrl: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "is_prescription")
    var is_prescription: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    companion object {
        fun mapFromEntityToDomain(product: ProductEntity): Product {
               return Product(product.id, product.name, product.price, product.imgUrl)
        }

    }

}