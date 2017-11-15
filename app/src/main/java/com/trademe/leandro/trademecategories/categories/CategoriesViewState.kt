package com.trademe.leandro.trademecategories.categories

import com.trademe.leandro.trademecategories.data.Category

/**
 * Created by Leandro on 14/11/2017.
 */
sealed class CategoriesViewState

object Loading : CategoriesViewState()
data class Failure(val error: Throwable) : CategoriesViewState()
data class Success(val category: Category) : CategoriesViewState()
