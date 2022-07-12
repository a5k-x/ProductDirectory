package com.ask.productdirectory.di

import android.content.Context
import com.ask.productdirectory.ui.DetailIngredientsFragment
import com.ask.productdirectory.ui.MainActivity
import com.ask.productdirectory.ui.SearchFragment
import com.ask.productdirectory.ui.TabContainerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        DataModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        LocalNavigationModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(searchFragment: SearchFragment)

    fun inject(detailIngredientsFragment: DetailIngredientsFragment)

    fun inject(tabContainerFragment: TabContainerFragment)

}