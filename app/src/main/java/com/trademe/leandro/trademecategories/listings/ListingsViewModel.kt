package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.trademe.leandro.trademecategories.SearchUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Leandro on 10/11/2017.
 */
class ListingsViewModel(
        categoryNumberObservable: Observable<String>,
        searchUseCase: SearchUseCase
) : ViewModel() {
    val viewState: MutableLiveData<ListingViewState> = MutableLiveData<ListingViewState>()

    private val disposables = CompositeDisposable()

    init {
        disposables.add(categoryNumberObservable
                .flatMap { searchUseCase.search(it) }
                .subscribe({ viewState.value = it }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory(
            private val categoryNumberObservable: Observable<String>,
            private val searchUseCase: SearchUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                ListingsViewModel(categoryNumberObservable, searchUseCase) as T
    }
}