package com.quoctran.pharmacies.domain.repository

import com.quoctran.pharmacies.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface IOderRepository {
    suspend fun getAllOder(): Flow<List<Order>>
    suspend fun addOrder(order: Order)
}