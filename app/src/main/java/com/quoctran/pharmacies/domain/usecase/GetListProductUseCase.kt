package com.quoctran.pharmacies.domain.usecase

import com.quoctran.pharmacies.domain.base.UseCase
import com.quoctran.pharmacies.domain.model.Product
import com.quoctran.pharmacies.domain.repository.IProductRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetListProductUseCase @Inject constructor(private val productRepository: IProductRepository):
    UseCase<List<Product>, UseCase.None>() {
    override suspend fun run(params: None) = productRepository.getAllProduct()
}