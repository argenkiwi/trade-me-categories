package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.ViewModelProviders
import com.trademe.leandro.trademecategories.TradeMeService
import dagger.Module
import dagger.Provides

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
object CategoriesModule {

    @JvmStatic
    @Provides
    fun viewModelFactory(
            service: TradeMeService
    ): CategoriesViewModel.Factory = CategoriesViewModel.Factory(service)

    @JvmStatic
    @Provides
    fun viewModel(
            fragment: CategoriesFragment,
            factory: CategoriesViewModel.Factory
    ) = ViewModelProviders.of(fragment.activity, factory).get(CategoriesViewModel::class.java)
}