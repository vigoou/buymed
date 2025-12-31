package com.quoctran.pharmacies.di

import android.content.Context
import androidx.room.Room
import com.quoctran.pharmacies.data.db.AppDatabase
import com.quoctran.pharmacies.data.db.OrderDao
import com.quoctran.pharmacies.data.db.OrderProductDao
import com.quoctran.pharmacies.data.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    private const val DATABASE_NAME = "pharmacies_db"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .createFromAsset("buymed.db")
            .build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideOrderDao(database: AppDatabase): OrderDao {
        return database.orderDao()
    }

    @Provides
    fun provideOrderProductDao(database: AppDatabase): OrderProductDao {
        return database.orderProductDao()
    }
}