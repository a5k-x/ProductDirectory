package com.ask.productdirectory.di

import com.ask.productdirectory.domain.repository.RepositoryIngredient
import com.ask.productdirectory.domain.usecase.ReadIngredientUseCase
import com.ask.productdirectory.domain.usecase.ReadNormIngredientUseCase
import com.ask.productdirectory.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideSearchUseCase(repository: RepositoryIngredient): SearchUseCase = SearchUseCase(repository)

    @Singleton
    @Provides
    fun provideReadIngredientUseCase(repository: RepositoryIngredient): ReadIngredientUseCase = ReadIngredientUseCase(repository)

    @Singleton
    @Provides
    fun provideReadNormIngredientUseCase(repository: RepositoryIngredient): ReadNormIngredientUseCase = ReadNormIngredientUseCase(repository)
}