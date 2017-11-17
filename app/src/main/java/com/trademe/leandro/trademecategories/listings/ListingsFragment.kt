package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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

class ListingsFragment : Fragment() {

    @Inject
    lateinit var viewModel: ListingsViewModel

    private lateinit var messageView: TextView
    private lateinit var listingsList: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_listings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageView = view.findViewById<TextView>(R.id.message)
        listingsList = view.findViewById<RecyclerView>(R.id.listings_list)
        listingsList.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            viewModel = ViewModelProviders.of(this).get(ListingsViewModel::class.java)
        } catch (error: Throwable) {
            AndroidSupportInjection.inject(this)
        }

        viewModel.viewState.observe(this, Observer {
            when (it) {
                is Success -> {
                    messageView.visibility = View.GONE
                    listingsList.visibility = View.VISIBLE
                    it.searchResult.list.let {
                        listingsList.adapter = ListingsAdapter(it)
                    }
                }
                else -> {
                    messageView.visibility = View.VISIBLE
                    listingsList.visibility = View.GONE
                    messageView.text = when (it) {
                        is Loading -> getString(R.string.loading)
                        is Failure -> it.error.localizedMessage
                        is Empty -> getString(R.string.no_listings_found)
                        else -> null
                    }
                }
            }
        })
    }
}