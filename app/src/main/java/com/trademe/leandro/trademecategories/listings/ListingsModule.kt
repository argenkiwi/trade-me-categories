package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.ViewModelProviders
import com.trademe.leandro.trademecategories.SearchUseCase
import com.trademe.leandro.trademecategories.TradeMeService
import dagger.Module
import dagger.Provides
import io.reactivex.Observable

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
object ListingsModule {

    @JvmStatic
    @Provides
    fun searchUseCase(service: TradeMeService) = SearchUseCase(service)

    @JvmStatic
    @Provides
    fun viewModelFactory(
            categoryNumberObservable: Observable<String>,
            searchUseCase: SearchUseCase
    ) = ListingsViewModel.Factory(categoryNumberObservable, searchUseCase)

    @JvmStatic
    @Provides
    fun viewModel(
            fragment: ListingsFragment,
            factory: ListingsViewModel.Factory
    ) = ViewModelProviders.of(fragment, factory).get(ListingsViewModel::class.java)
}