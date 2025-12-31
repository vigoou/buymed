package com.quoctran.pharmacies.domain.repository

import com.quoctran.pharmacies.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getAllProduct() : List<Product>
}