package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trademe.leandro.trademecategories.R
import com.trademe.leandro.trademecategories.data.Category
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CategoriesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: CategoriesViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.category.observe(this, Observer {
            it?.subcategories?.let { listSubcategories(it) }
        })
    }

    private fun listSubcategories(subcategories: List<Category>) {
        Log.d(CategoriesFragment::class.java.simpleName, subcategories.toString())
        // TODO Update list
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_categories, container, false)
}