package com.ask.productdirectory.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ask.productdirectory.ui.FragmentScreenTag
import com.ask.productdirectory.presentation.subnavigation.LocalCiceroneHolder

class MainViewModel(
    private val ciceroneHolder: LocalCiceroneHolder,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    fun onBackPressed() {
        ciceroneHolder.getCicerone(FragmentScreenTag.TIMETABLE.name).router.exit()
    }
}
