package com.quoctran.pharmacies.domain.model

data class Order(
    val id: Int,
    val customerName: String,
    val totalAmount: Long,
    val orderDate: String,
    val products: List<Product>
)
