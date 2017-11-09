package com.trademe.leandro.trademecategories.data

/**
 * Created by Leandro on 10/11/2017.
 */
data class Category(
        val name: String,
        val subcategories: List<Category> = ArrayList()
)