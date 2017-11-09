package com.trademe.leandro.trademecategories

import android.os.Bundle
import android.view.View
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (findViewById<View>(R.id.listings) != null
                && supportFragmentManager.findFragmentById(R.id.listings) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.listings, ListingsFragment())
                    .commit()
        }
    }
}
