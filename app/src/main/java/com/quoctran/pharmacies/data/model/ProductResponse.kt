package com.quoctran.pharmacies.data.model

import com.quoctran.pharmacies.domain.model.Product

data class ProductResponse (
    val id: Long,
    val name: String,
    val price: Long,
    val imgUrl: String,
    var code: String,
    var category: String,
    var is_prescription: String,
){
    companion object {
        fun mapFromResponseToDomain(listProductResponse: List<ProductResponse>?): List<Product> {
            return listProductResponse?.map {
                Product(it.id, it.name, it.price, it.imgUrl, 0, it.code, it.category, it.is_prescription)
            } ?: listOf()
        }
    }
}