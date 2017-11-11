package com.trademe.leandro.trademecategories.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Leandro on 11/11/2017.
 */
data class Listing(
        @SerializedName("ListingId") val id: Int,
        @SerializedName("Title") val title: String,
        @SerializedName("PictureHref") val pictureHref: String
)