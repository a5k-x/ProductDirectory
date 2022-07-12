package com.ask.productdirectory.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormIngredient
import com.ask.productdirectory.domain.usecase.ReadIngredientUseCase
import com.ask.productdirectory.domain.usecase.ReadNormIngredientUseCase
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailIngredientsViewModel(
    private val normUseCase: ReadNormIngredientUseCase,
    private val useCase: ReadIngredientUseCase,
    private val router: Router,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val readIngredientLiveData = MutableLiveData<Pair<Ingredient, NormIngredient>>()
    val _readIngredientLiveData: LiveData<Pair<Ingredient, NormIngredient>> = readIngredientLiveData

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("ERROR", exception.message.toString())
    }

    fun ingredient(id: Int) {
        viewModelScope.launch(handler) {
            val data = async { useCase(id) }
            val normData = async { normUseCase() }
            readIngredientLiveData.value = Pair(data.await(), normData.await())
        }
    }

    fun exit(){

    }
}