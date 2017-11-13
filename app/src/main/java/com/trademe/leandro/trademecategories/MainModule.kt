package com.trademe.leandro.trademecategories

import android.arch.lifecycle.ViewModelProviders
import com.trademe.leandro.trademecategories.categories.CategoriesFragment
import com.trademe.leandro.trademecategories.categories.CategoriesModule
import com.trademe.leandro.trademecategories.data.Category
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import com.trademe.leandro.trademecategories.listings.ListingsModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
abstract class MainModule {
    @ContributesAndroidInjector(modules = arrayOf(CategoriesModule::class))
    abstract fun categoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector(modules = arrayOf(ListingsModule::class))
    abstract fun listingsFragment(): ListingsFragment

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun mainViewModel(
                activity: MainActivity
        ): MainViewModel = ViewModelProviders.of(activity).get(MainViewModel::class.java)

        @JvmStatic
        @Provides
        fun categoryNumberObservable(
                mainViewModel: MainViewModel
        ): Observable<String> = mainViewModel.category.map { it.number }

        @JvmStatic
        @Provides
        fun categoryObserver(
                mainViewModel: MainViewModel
        ): Observer<Category> = mainViewModel.category

        @JvmStatic
        @Provides
        fun categoryObservable(
                mainViewModel: MainViewModel
        ): Observable<Category> = mainViewModel.category
    }
}