package com.ask.productdirectory.di.mvvm

import androidx.lifecycle.SavedStateHandle
import com.ask.productdirectory.domain.usecase.ReadIngredientUseCase
import com.ask.productdirectory.domain.usecase.ReadNormIngredientUseCase
import com.ask.productdirectory.presentation.viewmodel.DetailIngredientsViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class DetailIngredientsViewModelFactory @Inject constructor(
    private val normUseCase: ReadNormIngredientUseCase,
    private val useCase: ReadIngredientUseCase,
    private val router: Router,
) : ViewModelAssistedFactory<DetailIngredientsViewModel> {

    override fun create(stateHandle: SavedStateHandle) =
        DetailIngredientsViewModel(
            normUseCase,
            useCase,
            router,
            stateHandle
        )
}