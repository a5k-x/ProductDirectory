package com.ask.productdirectory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ask.productdirectory.databinding.FragmentAnalyticsBinding


class AnalyticsFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = AnalyticsFragment()
    }
    private var vb: FragmentAnalyticsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }
}