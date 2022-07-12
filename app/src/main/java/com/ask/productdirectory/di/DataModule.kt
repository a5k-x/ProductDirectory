package com.ask.productdirectory.di

import com.ask.productdirectory.data.datasource.IngredientsDataSource
import com.ask.productdirectory.data.datasource.IngredientsDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindRepository(impl: IngredientsDataSourceImpl): IngredientsDataSource
}