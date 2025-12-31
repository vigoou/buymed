package com.quoctran.pharmacies.di

import com.quoctran.pharmacies.data.repository.ProductRepositoryImpl
import com.quoctran.pharmacies.domain.repository.IProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindProductRepository(
        pharmacyRepositoryImpl: ProductRepositoryImpl
    ): IProductRepository
}