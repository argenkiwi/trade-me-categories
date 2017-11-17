package com.trademe.leandro.trademecategories

import android.arch.lifecycle.MutableLiveData
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

    @Binds
    abstract fun categoryObserver(subject: Subject<Category>): Observer<Category>

    @Binds
    abstract fun categoryObservable(subject: Subject<Category>): Observable<Category>

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun categorySubject(): Subject<Category> = BehaviorSubject.create()

        @JvmStatic
        @Provides
        fun breadcrumb() = MutableLiveData<List<Category>>()

        @JvmStatic
        @Provides
        fun viewModel(
                activity: MainActivity,
                factory: MainViewModel.Factory
        ): MainViewModel = ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)

        @JvmStatic
        @Provides
        fun categoryNumberObservable(
                observable: Observable<Category>
        ): Observable<String> = observable.map { it.number }
    }
}