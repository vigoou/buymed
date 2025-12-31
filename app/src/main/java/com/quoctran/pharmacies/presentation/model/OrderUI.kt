package com.quoctran.pharmacies.presentation.model

import com.quoctran.pharmacies.domain.model.Product

data class OrderUI(
    val id: Int,
    val customerName: String,
    val totalAmount: Long,
    val orderDate: String,
    val products: List<ProductUI>
) {
    companion object {
        fun mapFromDomainToUI(order: com.quoctran.pharmacies.domain.model.Order): OrderUI {
            return OrderUI(
                id = order.id,
                customerName = order.customerName,
                totalAmount = order.totalAmount,
                orderDate = order.orderDate,
                products = order.products.map { product ->
                    ProductUI.mapFromDomainToUI(product)
                }
            )
        }

        fun mapFromUIToDomain(orderUI: OrderUI): com.quoctran.pharmacies.domain.model.Order {
            return com.quoctran.pharmacies.domain.model.Order(
                id = orderUI.id,
                customerName = orderUI.customerName,
                totalAmount = orderUI.totalAmount,
                orderDate = orderUI.orderDate,
                products = orderUI.products.map { productUI ->
                    ProductUI.mapFromUIToDomain(productUI)
                }
            )
        }

    }
}
