package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
object CategoriesModule {
    @JvmStatic
    @Provides
    fun viewModel(fragment: CategoriesFragment) =
            ViewModelProviders.of(fragment).get(CategoriesViewModel::class.java)
}