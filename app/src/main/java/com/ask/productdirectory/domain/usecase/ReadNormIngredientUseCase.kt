package com.ask.productdirectory.domain.usecase

import com.ask.productdirectory.domain.entity.NormIngredient
import com.ask.productdirectory.domain.repository.RepositoryIngredient
import javax.inject.Inject

class ReadNormIngredientUseCase @Inject constructor(
    private val repository: RepositoryIngredient
) {

    suspend operator fun invoke(): NormIngredient =
        repository.readNormIngredient()
}