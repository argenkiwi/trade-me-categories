package com.trademe.leandro.trademecategories.listings

import com.trademe.leandro.trademecategories.data.SearchResult

/**
 * Created by Leandro on 13/11/2017.
 */
data class ListingViewState(
        val isLoading: Boolean,
        val searchResult: SearchResult? = null,
        val error: Throwable? = null
)