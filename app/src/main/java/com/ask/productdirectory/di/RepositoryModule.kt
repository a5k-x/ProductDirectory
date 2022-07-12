package com.ask.productdirectory.di

import com.ask.productdirectory.data.repository.RepositoryIngredientImpl
import com.ask.productdirectory.domain.repository.RepositoryIngredient
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(impl: RepositoryIngredientImpl): RepositoryIngredient
}