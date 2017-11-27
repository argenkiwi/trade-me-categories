package com.trademe.leandro.trademecategories.categories

import com.trademe.leandro.trademecategories.data.Category

/**
 * Created by Leandro on 27/11/2017.
 */
sealed class CategoriesEvent {
    object LoadCategories : CategoriesEvent()
    data class LoadCategoriesError(val error: Throwable) : CategoriesEvent()
    data class CategoriesLoaded(val category: Category) : CategoriesEvent()
}