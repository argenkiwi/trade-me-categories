package com.trademe.leandro.trademecategories.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Leandro on 10/11/2017.
 */
data class Category(
        @SerializedName("Number") val number: String,
        @SerializedName("Name") val name: String,
        @SerializedName("Subcategories") val subcategories: List<Category>,
        @SerializedName("IsLeaf") val isLeaf: Boolean
)