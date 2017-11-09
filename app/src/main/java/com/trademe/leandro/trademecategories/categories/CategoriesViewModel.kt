package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.trademe.leandro.trademecategories.data.Category

/**
 * Created by Leandro on 10/11/2017.
 */
class CategoriesViewModel : ViewModel() {
    val category: LiveData<Category> by lazy {
        val category = MutableLiveData<Category>()
        category.value = Category("Root", arrayListOf(
                Category("Trade Me Motors"),
                Category("Trade Me Properties"),
                Category("Trade Me Jobs")
        ))
        category
    }
}