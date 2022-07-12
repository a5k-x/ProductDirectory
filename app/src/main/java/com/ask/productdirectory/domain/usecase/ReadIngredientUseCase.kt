package com.ask.productdirectory.domain.usecase

import com.ask.productdirectory.domain.repository.RepositoryIngredient
import javax.inject.Inject

class ReadIngredientUseCase @Inject constructor(
    private val repository: RepositoryIngredient
) {

    suspend operator fun invoke(id: Int) =
        repository.readIngredient(id)
}