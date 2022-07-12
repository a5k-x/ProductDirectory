package com.ask.productdirectory.di

import android.content.Context
import com.ask.productdirectory.data.database.AppDatabase
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(appContext: Context): AppDatabase = AppDatabase.getDatabase(appContext)
}
