package com.ask.productdirectory.data.repository

import com.ask.productdirectory.data.datasource.IngredientsDataSource
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormIngredient
import com.ask.productdirectory.domain.repository.RepositoryIngredient
import javax.inject.Inject

class RepositoryIngredientImpl @Inject constructor(
    private val dataSource: IngredientsDataSource
) : RepositoryIngredient {

    override suspend fun getIngredient(name: String, source: Boolean): List<Ingredient> =
        dataSource.getIngredient(name, source).filter {
            val flag = it.name.lowercase().indexOf(name)>=0
            return@filter flag
        }

    override suspend fun readIngredient(id: Int): Ingredient =
        dataSource.readIngredient(id)

    override suspend fun readNormIngredient(): NormIngredient =
        dataSource.readNormIngredient()
}