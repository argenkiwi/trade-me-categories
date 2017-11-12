package com.trademe.leandro.trademecategories

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<View>(R.id.fab)?.setOnClickListener {
            startActivity(Intent(this, ListingsActivity::class.java))
        }

        if (findViewById<View>(R.id.listings) != null
                && supportFragmentManager.findFragmentById(R.id.listings) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.listings, ListingsFragment())
                    .commit()
        }
    }
}
