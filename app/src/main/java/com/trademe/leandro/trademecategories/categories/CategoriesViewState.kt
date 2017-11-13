package com.trademe.leandro.trademecategories.categories

import com.trademe.leandro.trademecategories.data.Category

/**
 * Created by Leandro on 14/11/2017.
 */
data class CategoriesViewState(
        val isLoading: Boolean,
        val category: Category? = null,
        val error: Throwable? = null
)