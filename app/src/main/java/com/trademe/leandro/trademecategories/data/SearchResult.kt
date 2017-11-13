package com.trademe.leandro.trademecategories.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Leandro on 11/11/2017.
 */
data class SearchResult(
        @SerializedName("TotalCount") val totalCount: Int,
        @SerializedName("List") val list: List<Listing>
)