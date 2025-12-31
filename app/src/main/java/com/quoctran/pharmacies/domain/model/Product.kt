package com.quoctran.pharmacies.domain.model

data class Product(
    val id: Long,
    val name: String,
    val price: Long,
    val imgUrl: String,
    val quantity: Int = 0,
    val code: String = "",
    val category: String = "",
    val is_prescription: String = ""
)
