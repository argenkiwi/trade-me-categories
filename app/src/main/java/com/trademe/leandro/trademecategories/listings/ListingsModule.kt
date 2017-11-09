package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
object ListingsModule {
    @JvmStatic
    @Provides
    fun viewModel(fragment: ListingsFragment) =
            ViewModelProviders.of(fragment).get(ListingsViewModel::class.java)
}