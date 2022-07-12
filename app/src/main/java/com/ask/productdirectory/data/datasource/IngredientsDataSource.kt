package com.ask.productdirectory.data.datasource

import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormIngredient

interface IngredientsDataSource {

    suspend fun getIngredient(name: String, flag: Boolean): List<Ingredient>

    suspend fun readIngredient(id: Int): Ingredient

    suspend fun readNormIngredient(): NormIngredient
}