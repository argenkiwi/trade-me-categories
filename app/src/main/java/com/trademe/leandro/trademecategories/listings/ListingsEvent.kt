package com.trademe.leandro.trademecategories.listings

import com.trademe.leandro.trademecategories.data.SearchResult

/**
 * Created by Leandro on 27/11/2017.
 */
sealed class ListingsEvent {
    object LoadListings : ListingsEvent()
    data class LoadListingsError(val error: Throwable) : ListingsEvent()
    data class ListingsLoaded(val searchResult: SearchResult) : ListingsEvent()
}