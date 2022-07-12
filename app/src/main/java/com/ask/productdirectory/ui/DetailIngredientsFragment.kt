package com.ask.productdirectory.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ask.productdirectory.di.mvvm.DetailIngredientsViewModelFactory
import com.ask.productdirectory.di.mvvm.GenericSavedStateViewModelFactory
import com.ask.productdirectory.App
import com.ask.productdirectory.databinding.FragmentDetailIngredientsBinding
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.domain.entity.NormIngredient
import com.ask.productdirectory.presentation.viewmodel.DetailIngredientsViewModel
import com.ask.productdirectory.ui.common.BackButtonListener
import javax.inject.Inject

private const val ARG_ID = "id"

class DetailIngredientsFragment : Fragment(), BackButtonListener {

    companion object {

        @JvmStatic
        fun newInstance(id: Int) = DetailIngredientsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }

    private var idIngredient: Int? = null

    @Inject
    lateinit var viewModelFactory: DetailIngredientsViewModelFactory
    private val viewModel: DetailIngredientsViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }
    private var vb: FragmentDetailIngredientsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idIngredient = it.getInt(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentDetailIngredientsBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel._readIngredientLiveData.observe(viewLifecycleOwner, ::render)
    }

    override fun onResume() {
        super.onResume()
        if (idIngredient != null) {
            viewModel.ingredient(idIngredient!!)
        }
    }

    private fun render(pair: Pair<Ingredient, NormIngredient>) {
          vb?.graphIngredients?.setTask(pair.first, pair.second)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }

    override fun onBackPressed(): Boolean {
        viewModel.exit()
        return true
    }
}