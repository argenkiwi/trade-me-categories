package com.trademe.leandro.trademecategories

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Leandro on 10/11/2017.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBindingsModule::class
))
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
