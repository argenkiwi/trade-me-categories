package com.trademe.leandro.trademecategories.listings

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trademe.leandro.trademecategories.R
import com.trademe.leandro.trademecategories.data.Listing

/**
 * Created by Leandro on 11/11/2017.
 */
class ListingsAdapter(
        private val listings: List<Listing>
) : RecyclerView.Adapter<ListingsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        // TODO Bind data to view.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val listingView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_listing, parent, false) as View
        return ViewHolder(listingView)
    }

    override fun getItemCount() = listings.size

    class ViewHolder(
            val listingView: View
    ) : RecyclerView.ViewHolder(listingView) {

    }
}