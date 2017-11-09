package com.trademe.leandro.trademecategories

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Leandro on 10/11/2017.
 */

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().create(this)
}
