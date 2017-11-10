package com.trademe.leandro.trademecategories.categories

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.trademe.leandro.trademecategories.data.Category


/**
 * Created by Leandro on 10/11/2017.
 */
class CategoriesAdapter(
        private val categories: List<Category>
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.nameView?.text = categories[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent?.context))
    }

    override fun getItemCount(): Int {
        return categories.size;
    }

    class ViewHolder(val nameView: TextView) : RecyclerView.ViewHolder(nameView)
}