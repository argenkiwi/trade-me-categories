package com.trademe.leandro.trademecategories.categories

import com.trademe.leandro.trademecategories.data.Category

/**
 * Created by Leandro on 14/11/2017.
 */
sealed class CategoriesState {
    object Loading : CategoriesState()
    data class Failure(val error: Throwable) : CategoriesState()
    data class Success(val category: Category) : CategoriesState()
}