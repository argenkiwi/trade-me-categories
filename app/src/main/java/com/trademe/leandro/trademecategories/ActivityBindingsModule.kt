package com.trademe.leandro.trademecategories

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Leandro on 10/11/2017.
 */
@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}