package com.strategycorps.medlite.di

import android.content.Context
import androidx.room.Room
import com.strategycorps.medlite.data.database.ProductDatabase
import com.strategycorps.medlite.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ProductDatabase) = database.productDao()

    @Singleton
    @Provides
    fun provideCategoryDao(database: ProductDatabase) = database.categoryDao()

}