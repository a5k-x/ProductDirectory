package com.ask.productdirectory.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ask.productdirectory.App
import com.ask.productdirectory.R
import com.ask.productdirectory.databinding.ActivityMainBinding
import com.ask.productdirectory.di.mvvm.GenericSavedStateViewModelFactory
import com.ask.productdirectory.di.mvvm.MainViewModelFactory
import com.ask.productdirectory.presentation.AndroidScreens
import com.ask.productdirectory.presentation.viewmodel.MainViewModel
import com.ask.productdirectory.ui.common.BackButtonListener
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var vb: ActivityMainBinding? = null

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = GenericSavedStateViewModelFactory(viewModelFactory, this).create(MainViewModel::class.java)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        vb?.bottomNavigation?.itemIconTintList = null
        listenerBottomMenu()
        selectTab(FragmentScreenTag.TIMETABLE)
    }

    private fun listenerBottomMenu() {
        vb?.bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.direction -> selectTab(FragmentScreenTag.TIMETABLE)
                R.id.profile -> selectTab(FragmentScreenTag.PROFILE)
                R.id.favorites -> selectTab(FragmentScreenTag.FAVORITES)
            }
            true
        }
    }

    private fun selectTab(tab: FragmentScreenTag) {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fm.findFragmentByTag(tab.name)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return
        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(R.id.ab_container, AndroidScreens.tab(tab.name).createFragment(fm.fragmentFactory), tab.name)
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commit()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            viewModel.onBackPressed()
        }
    }

    override fun onDestroy() {
        vb = null
        super.onDestroy()
    }
}

enum class FragmentScreenTag {
    TIMETABLE,
    FAVORITES,
    PROFILE
}