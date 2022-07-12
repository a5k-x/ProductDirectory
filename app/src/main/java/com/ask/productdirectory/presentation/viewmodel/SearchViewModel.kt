package com.ask.productdirectory.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.usecase.SearchUseCase
import com.ask.productdirectory.presentation.AndroidScreens
import com.ask.productdirectory.ui.FragmentScreenTag
import com.ask.productdirectory.presentation.subnavigation.LocalCiceroneHolder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchViewModel(
    private val useCase: SearchUseCase,
    private val ciceroneHolder: LocalCiceroneHolder,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val searchLiveData = MutableLiveData<List<Ingredient>>()
    val _searchLiveData: LiveData<List<Ingredient>> = searchLiveData

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("ERROR", exception.message.toString())
    }

    fun search(name: String, source: Boolean) {
        viewModelScope.launch(handler) {
            val data = useCase(name, source)
            searchLiveData.value = data
        }
    }

    fun onClickItem(id:Int){
        ciceroneHolder.getCicerone(FragmentScreenTag.TIMETABLE.name).router.navigateTo(AndroidScreens.ingredients(id))
    }
}