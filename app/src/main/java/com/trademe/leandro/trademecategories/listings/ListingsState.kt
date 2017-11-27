package com.trademe.leandro.trademecategories.listings

import com.trademe.leandro.trademecategories.data.SearchResult

/**
 * Created by Leandro on 13/11/2017.
 */
sealed class ListingsState {
    object Loading : ListingsState()
    data class Failure(val error: Throwable) : ListingsState()
    object Empty : ListingsState()
    data class Success(val searchResult: SearchResult) : ListingsState()
}
