package com.quoctran.pharmacies.data.network

import com.quoctran.pharmacies.data.model.OrderResponse
import com.quoctran.pharmacies.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getListProduct(): Response<List<ProductResponse>>

    @GET("orders")
    suspend fun getListOrder(): Response<List<OrderResponse>>
}