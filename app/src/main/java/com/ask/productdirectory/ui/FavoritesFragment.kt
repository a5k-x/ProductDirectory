package com.ask.productdirectory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ask.productdirectory.databinding.FragmentMenuBinding

class FavoritesFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }

    private var vb: FragmentMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentMenuBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }
}