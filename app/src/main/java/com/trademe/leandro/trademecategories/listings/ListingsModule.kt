package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import com.trademe.leandro.trademecategories.SearchUseCase
import com.trademe.leandro.trademecategories.TradeMeService
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

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
    fun disposables() = CompositeDisposable()

    @JvmStatic
    @Provides
    fun viewState() = MutableLiveData<ListingsViewState>()

    @JvmStatic
    @Provides
    fun viewModel(
            fragment: ListingsFragment,
            factory: ListingsViewModel.Factory
    ) = ViewModelProviders.of(fragment, factory).get(ListingsViewModel::class.java)
}