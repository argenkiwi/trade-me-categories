package com.trademe.leandro.trademecategories

import android.arch.lifecycle.ViewModelProviders
import com.trademe.leandro.trademecategories.categories.CategoriesFragment
import com.trademe.leandro.trademecategories.categories.CategoriesModule
import com.trademe.leandro.trademecategories.data.Category
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import com.trademe.leandro.trademecategories.listings.ListingsModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

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
        @ActivityScope
        fun categorySubject(): Subject<Category> = BehaviorSubject.create()

        @JvmStatic
        @Provides
        @ActivityScope
        fun viewModelFactory(subject: Subject<Category>) = MainViewModel.Factory(subject, subject)

        @JvmStatic
        @Provides
        fun viewModel(
                activity: MainActivity,
                factory: MainViewModel.Factory
        ) = ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)

        @JvmStatic
        @Provides
        fun categoryObserver(viewModel: MainViewModel): Observer<Category> =
                viewModel.categoryObserver

        @JvmStatic
        @Provides
        fun categoryObservable(viewModel: MainViewModel): Observable<Category> =
                viewModel.categoryObservable

        @JvmStatic
        @Provides
        fun categoryNumberObservable(
                observable: Observable<Category>
        ): Observable<String> = observable.map { it.number }
    }
}