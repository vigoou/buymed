package com.quoctran.pharmacies.presentation.model

import com.quoctran.pharmacies.domain.model.Product

data class ProductUI(
    val id: Long,
    val name: String,
    val price: Long,
    val imgUrl: String,
    val quantity: Int = 0,
    val code: String = "",
    val category: String = "",
    val is_prescription: String = ""
) {
    companion object {
        fun mapFromDomainToUI(product: Product): ProductUI {
            return ProductUI(
                id = product.id,
                name = product.name,
                price = product.price,
                imgUrl = product.imgUrl,
                quantity = product.quantity,
                code = product.code,
                category = product.category,
                is_prescription = product.is_prescription
            )
        }

        fun mapFromUIToDomain(productUI: ProductUI): Product {
            return Product(
                id = productUI.id,
                name = productUI.name,
                price = productUI.price,
                imgUrl = productUI.imgUrl,
                quantity = productUI.quantity,
                code = productUI.code,
                category = productUI.category,
                is_prescription = productUI.is_prescription
            )
        }
    }
}
