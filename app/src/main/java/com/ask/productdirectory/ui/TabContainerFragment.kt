package com.ask.productdirectory.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ask.productdirectory.App
import com.ask.productdirectory.R
import com.ask.productdirectory.presentation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.ask.productdirectory.presentation.subnavigation.LocalCiceroneHolder
import javax.inject.Inject

class TabContainerFragment : Fragment() {

    companion object {
        private const val EXTRA_NAME = "tcf_extra_name"

        fun newInstance(name: String) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                }
            }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder
    lateinit var containerName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            containerName = it.getString(EXTRA_NAME).toString()
        }
    }

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder.getCicerone(containerName)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            when (containerName) {
                FragmentScreenTag.FAVORITES.name -> cicerone.router.replaceScreen(AndroidScreens.favorites())
                FragmentScreenTag.PROFILE.name -> cicerone.router.replaceScreen(AndroidScreens.profile())
                else -> cicerone.router.replaceScreen(AndroidScreens.search())
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(
            AppNavigator(requireActivity(), R.id.ftc_container, childFragmentManager)
        )
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }
}