package com.trademe.leandro.trademecategories

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private val disposables = CompositeDisposable()
    private var searchButton: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        searchButton = findViewById<View>(R.id.fab)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        disposables.add(viewModel.category.subscribe(Consumer {
            val categoryName = it.name
            val categoryNumber = it.number
            searchButton?.setOnClickListener {
                startActivity(ListingsActivity.newIntent(this, categoryName, categoryNumber))
            }
        }))

        if (findViewById<View>(R.id.listings) != null
                && supportFragmentManager.findFragmentById(R.id.listings) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.listings, ListingsFragment())
                    .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
