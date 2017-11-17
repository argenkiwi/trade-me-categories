package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Leandro on 10/11/2017.
 */
class ListingsViewModel(
        val viewState: LiveData<ListingsViewState>
) : ViewModel() {
    class Factory @Inject constructor(
            private val viewState: LiveData<ListingsViewState>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                ListingsViewModel(viewState) as T
    }
}