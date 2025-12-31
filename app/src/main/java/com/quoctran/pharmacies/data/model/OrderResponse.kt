package com.quoctran.pharmacies.data.model

import com.quoctran.pharmacies.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

data class OrderResponse(
    val id: Long,
    val customerName: String,
    val totalAmount: Long,
    val orderDate: Date,
) {
    companion object {
        fun mapFromEntityToDomain(listProductResponse: Flow<List<ProductResponse>>): Flow<List<Product>> {
            return listProductResponse.map { product ->
                product.map {
                    Product(it.id, it.name, it.price, it.imgUrl)
                }
            }
        }
    }
}