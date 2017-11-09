package com.trademe.leandro.trademecategories.listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trademe.leandro.trademecategories.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListingsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: ListingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater?.inflate(R.layout.fragment_listings, container, false)
}