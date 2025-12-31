package com.quoctran.pharmacies.di

import com.quoctran.pharmacies.domain.repository.IProductRepository
import com.quoctran.pharmacies.domain.usecase.GetListProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetListProductUseCase(productRepository: IProductRepository): GetListProductUseCase {
        return GetListProductUseCase(productRepository)
    }
}