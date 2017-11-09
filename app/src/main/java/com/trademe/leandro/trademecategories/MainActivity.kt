package com.trademe.leandro.trademecategories

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.trademe.leandro.trademecategories.listings.ListingsFragment

class MainActivity : AppCompatActivity() {

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
