package com.trademe.leandro.trademecategories

import android.os.Bundle
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import com.trademe.leandro.trademecategories.listings.ListingsModule
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable

class ListingsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listings)
    }

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector(modules = arrayOf(ListingsModule::class))
        abstract fun listingsFragment(): ListingsFragment

        @dagger.Module
        companion object {
            @JvmStatic
            @Provides
            fun categoryNumberObservable(): Observable<String> = Observable.just("")
        }
    }
}
