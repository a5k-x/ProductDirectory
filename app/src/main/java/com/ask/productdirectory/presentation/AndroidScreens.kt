package com.ask.productdirectory.presentation

import com.ask.productdirectory.ui.*
import com.github.terrakok.cicerone.androidx.FragmentScreen


object AndroidScreens {

    fun ingredients(id: Int) = FragmentScreen {
        DetailIngredientsFragment.newInstance(id)
    }

    fun tab(tag: String) = FragmentScreen {
        TabContainerFragment.newInstance(tag)
    }

    fun search() = FragmentScreen {
        SearchFragment.newInstance()
    }

    fun favorites() = FragmentScreen {
        FavoritesFragment.newInstance()
    }

    fun profile() = FragmentScreen {
        AnalyticsFragment.newInstance()
    }
}