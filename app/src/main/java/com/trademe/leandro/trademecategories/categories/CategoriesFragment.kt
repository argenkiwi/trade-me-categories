package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

    private lateinit var categoryList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.category.observe(this, Observer {
            it?.subcategories?.let { listSubcategories(it) }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryList = view.findViewById<RecyclerView>(R.id.category_list);
        categoryList.layoutManager = LinearLayoutManager(context)
    }

    private fun listSubcategories(subcategories: List<Category>) {
        categoryList.adapter = CategoriesAdapter(subcategories, {
            viewModel.onCategorySelected(it)
        })
    }
}