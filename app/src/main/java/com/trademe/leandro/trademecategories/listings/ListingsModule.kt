package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.ViewModelProviders
import com.trademe.leandro.trademecategories.TradeMeService
import dagger.Module
import dagger.Provides

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
object ListingsModule {
    @JvmStatic
    @Provides
    fun viewModelFactory(service: TradeMeService) = ListingsViewModel.Factory(service)

    @JvmStatic
    @Provides
    fun viewModel(fragment: ListingsFragment, factory: ListingsViewModel.Factory) =
            ViewModelProviders.of(fragment.activity, factory).get(ListingsViewModel::class.java)
}