package com.trademe.leandro.trademecategories

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
abstract class ActivityBindingsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(ListingsActivity.Module::class))
    abstract fun listingsActivity(): ListingsActivity
}