package com.trademe.leandro.trademecategories

import android.content.Context
import android.content.Intent
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

    companion object {
        private val EXTRA_CATEGORY_NUMBER = "extra_category_number"

        fun newIntent(
                context: Context,
                categoryNumber: String
        ): Intent = Intent(context, ListingsActivity::class.java)
                .putExtra(EXTRA_CATEGORY_NUMBER, categoryNumber)
    }

    @dagger.Module
    abstract class Module {
        @ContributesAndroidInjector(modules = arrayOf(ListingsModule::class))
        abstract fun listingsFragment(): ListingsFragment

        @dagger.Module
        companion object {
            @JvmStatic
            @Provides
            fun categoryNumberObservable(
                    activity: ListingsActivity
            ): Observable<String> {
                val categoryNumber = activity.intent.extras
                        .getString(ListingsActivity.EXTRA_CATEGORY_NUMBER, "")
                return Observable.just(categoryNumber)
            }
        }
    }
}
