package com.ask.productdirectory.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ask.productdirectory.di.mvvm.GenericSavedStateViewModelFactory
import com.ask.productdirectory.di.mvvm.SearchViewModelFactory
import com.ask.productdirectory.App
import com.ask.productdirectory.databinding.FragmentTimetableBinding
import com.ask.productdirectory.domain.entity.Ingredient
import com.ask.productdirectory.presentation.viewmodel.SearchViewModel
import com.ask.productdirectory.ui.adapter.SearchAdapter
import javax.inject.Inject

class SearchFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory
    private val viewModel: SearchViewModel by viewModels {
        GenericSavedStateViewModelFactory(viewModelFactory, this)
    }

    private var vb: FragmentTimetableBinding? = null
    private val adapter: SearchAdapter = SearchAdapter(::onClickItem)

    private fun onClickItem(id: Int) {
        viewModel.onClickItem(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentTimetableBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel._searchLiveData.observe(viewLifecycleOwner, ::render)
        initListenerSearch()
        initAdapter()
        hideSystemKeyboard()
    }

    private fun render(list: List<Ingredient>) {
        adapter.settingAdapter(list)
    }

    //скрыть клавиатуру
    private fun hideSystemKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        vb?.searchRecyclerContainer?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                vb?.fieldSearch?.clearFocus()
                imm.hideSoftInputFromWindow(vb?.searchRecyclerContainer!!.windowToken, 0)
            }
        })
    }

    private fun initListenerSearch() {
        val switchSource = vb?.sourceSwitch
        var name = ""
        switchSource?.setOnClickListener {
            name = vb?.fieldSearch?.text.toString()
            viewModel.search(name, switchSource.isChecked)
        }
        vb?.fieldSearch?.doAfterTextChanged {
            viewModel.search(it.toString(), switchSource?.isChecked ?: true)
        }
    }

    private fun initAdapter() {
        vb?.searchRecyclerContainer?.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }
}