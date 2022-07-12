package com.ask.productdirectory.domain.repository

import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormIngredient

interface RepositoryIngredient {

    suspend fun getIngredient(name: String, source: Boolean): List<Ingredient>

    suspend fun readIngredient(id: Int): Ingredient

    suspend fun readNormIngredient(): NormIngredient
}