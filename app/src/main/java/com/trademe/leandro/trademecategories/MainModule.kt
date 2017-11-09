package com.trademe.leandro.trademecategories

import com.trademe.leandro.trademecategories.categories.CategoriesFragment
import com.trademe.leandro.trademecategories.categories.CategoriesModule
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import com.trademe.leandro.trademecategories.listings.ListingsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
abstract class MainModule {
    @ContributesAndroidInjector(modules = arrayOf(CategoriesModule::class))
    abstract fun categoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector(modules = arrayOf(ListingsModule::class))
    abstract fun listingsFragment(): ListingsFragment
}