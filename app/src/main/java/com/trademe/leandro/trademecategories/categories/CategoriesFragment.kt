package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trademe.leandro.trademecategories.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CategoriesFragment : Fragment() {

    @Inject
    lateinit var viewModel: CategoriesViewModel

    private lateinit var messageView: TextView
    private lateinit var categoryList: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageView = view.findViewById<TextView>(R.id.message)
        categoryList = view.findViewById<RecyclerView>(R.id.category_list)
        categoryList.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        viewModel.viewState.observe(this, Observer {
            when (it) {
                is Loading -> {
                    messageView.visibility = View.VISIBLE
                    messageView.setText(R.string.loading)
                    categoryList.visibility = View.GONE
                }
                is Failure -> {
                    messageView.visibility = View.VISIBLE
                    messageView.text = it.error.localizedMessage
                    categoryList.visibility = View.GONE
                }
                is Success -> {
                    messageView.visibility = View.GONE
                    categoryList.visibility = View.VISIBLE
                    it.category.subcategories.let {
                        categoryList.adapter = CategoriesAdapter(it, {
                            viewModel.onCategorySelected(it)
                        })
                    }
                }
            }
        })
    }
}