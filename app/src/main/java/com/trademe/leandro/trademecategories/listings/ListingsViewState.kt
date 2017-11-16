package com.trademe.leandro.trademecategories.listings

import com.trademe.leandro.trademecategories.data.SearchResult

/**
 * Created by Leandro on 13/11/2017.
 */
sealed class ListingsViewState

object Loading : ListingsViewState()
data class Failure(val error: Throwable) : ListingsViewState()
object Empty : ListingsViewState()
data class Success(val searchResult: SearchResult) : ListingsViewState()
