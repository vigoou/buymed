package com.quoctran.pharmacies.data.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "order"
)
data class OrderEntity(
    @ColumnInfo(name = "customer_name")
    var customerName: String,

    @ColumnInfo(name = "total_amount")
    var totalAmount: Long,

    @ColumnInfo(name = "order_date")
    var orderDate: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

}
