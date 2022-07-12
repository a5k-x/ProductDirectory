package com.ask.productdirectory.di.mvvm

import androidx.lifecycle.SavedStateHandle
import com.ask.productdirectory.domain.usecase.SearchUseCase
import com.ask.productdirectory.presentation.viewmodel.SearchViewModel
import com.ask.productdirectory.presentation.subnavigation.LocalCiceroneHolder
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
    private val ciceroneHolder: LocalCiceroneHolder,
    private val useCase: SearchUseCase
) : ViewModelAssistedFactory<SearchViewModel> {

    override fun create(stateHandle: SavedStateHandle) =
        SearchViewModel(
            useCase,
            ciceroneHolder,
            stateHandle
        )
}