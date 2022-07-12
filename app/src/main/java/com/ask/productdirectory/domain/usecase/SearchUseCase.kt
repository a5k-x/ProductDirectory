package com.ask.productdirectory.domain.usecase

import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.repository.RepositoryIngredient
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: RepositoryIngredient
) {

    suspend operator fun invoke(name: String, source: Boolean): List<Ingredient> =
        repository.getIngredient(name, source)
}