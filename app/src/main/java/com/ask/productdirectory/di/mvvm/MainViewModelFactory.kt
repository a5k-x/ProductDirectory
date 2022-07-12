package com.ask.productdirectory.di.mvvm

import androidx.lifecycle.SavedStateHandle
import com.ask.productdirectory.presentation.viewmodel.MainViewModel
import com.ask.productdirectory.presentation.subnavigation.LocalCiceroneHolder
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val ciceroneHolder: LocalCiceroneHolder,
) : ViewModelAssistedFactory<MainViewModel> {

    override fun create(stateHandle: SavedStateHandle) =
        MainViewModel(
            ciceroneHolder,
            stateHandle
        )
}