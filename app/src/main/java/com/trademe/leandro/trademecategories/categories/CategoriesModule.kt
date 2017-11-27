package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
object CategoriesModule {

    @JvmStatic
    @Provides
    fun disposables() = CompositeDisposable()

    @JvmStatic
    @Provides
    fun viewState() = MutableLiveData<CategoriesState>()

    @JvmStatic
    @Provides
    fun viewModel(
            fragment: CategoriesFragment,
            factory: CategoriesViewModel.Factory
    ) = ViewModelProviders.of(fragment, factory).get(CategoriesViewModel::class.java)
}