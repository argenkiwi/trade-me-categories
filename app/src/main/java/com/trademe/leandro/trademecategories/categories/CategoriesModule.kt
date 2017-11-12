package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.ViewModelProviders
import com.trademe.leandro.trademecategories.TradeMeService
import com.trademe.leandro.trademecategories.data.Category
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
object CategoriesModule {

    @JvmStatic
    @Provides
    fun viewModelFactory(
            categoryObserver: Observer<Category>,
            categoryObservable: Observable<Category>,
            service: TradeMeService
    ): CategoriesViewModel.Factory = CategoriesViewModel
            .Factory(categoryObserver, categoryObservable, service)

    @JvmStatic
    @Provides
    fun viewModel(
            fragment: CategoriesFragment,
            factory: CategoriesViewModel.Factory
    ) = ViewModelProviders.of(fragment.activity, factory).get(CategoriesViewModel::class.java)
}