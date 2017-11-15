package com.trademe.leandro.trademecategories.listings

import com.trademe.leandro.trademecategories.data.SearchResult

/**
 * Created by Leandro on 13/11/2017.
 */
sealed class ListingViewState

object Loading : ListingViewState()
data class Failure(val error: Throwable) : ListingViewState()
data class Success(val searchResult: SearchResult) : ListingViewState()
