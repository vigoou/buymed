package com.quoctran.pharmacies.data.repository

import com.quoctran.pharmacies.data.db.ProductDao
import com.quoctran.pharmacies.data.db.entity.ProductEntity.Companion.mapFromEntityToDomain
import com.quoctran.pharmacies.domain.model.Product
import com.quoctran.pharmacies.domain.repository.IProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    IProductRepository {
    override suspend fun getAllProduct(): List<Product> {
        val productList  = productDao.getListProduct().map { productEntity ->
            mapFromEntityToDomain(productEntity)
        }
        return productList
    }
}
