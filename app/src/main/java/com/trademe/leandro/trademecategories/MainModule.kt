package com.trademe.leandro.trademecategories

import com.trademe.leandro.trademecategories.categories.CategoriesFragment
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
abstract class MainModule {
    @ContributesAndroidInjector
    abstract fun categoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector
    abstract fun listingsFragment(): ListingsFragment
}