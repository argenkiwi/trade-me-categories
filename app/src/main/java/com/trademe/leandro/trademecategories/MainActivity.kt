package com.trademe.leandro.trademecategories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trademe.leandro.trademecategories.categories.CategoriesAdapter
import com.trademe.leandro.trademecategories.listings.ListingsFragment
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        } catch (error: Throwable) {
            AndroidInjection.inject(this)
        }

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        breadcrumb.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.breadcrumb.observe(this, Observer {
            it?.let {
                breadcrumb.adapter = CategoriesAdapter(it, {
                    viewModel.onBreadcrumbItemClicked(it)
                })
                breadcrumb.scrollToPosition(it.lastIndex)

                it.lastOrNull()?.let {
                    val categoryName = it.name
                    val categoryNumber = it.number
                    fab?.setOnClickListener {
                        startActivity(ListingsActivity
                                .newIntent(this, categoryName, categoryNumber))
                    }
                }
            }
        })

        if (findViewById<View>(R.id.listings) != null
                && supportFragmentManager.findFragmentById(R.id.listings) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.listings, ListingsFragment())
                    .commit()
        }
    }

    override fun supportFragmentInjector() = viewModel.fragmentInjector
}
