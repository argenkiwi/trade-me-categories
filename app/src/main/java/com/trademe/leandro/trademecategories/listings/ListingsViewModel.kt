package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.trademe.leandro.trademecategories.SearchUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Leandro on 10/11/2017.
 */
class ListingsViewModel(
        categoryNumberObservable: Observable<String>,
        searchUseCase: SearchUseCase,
        private val disposables: CompositeDisposable,
        val viewState: MutableLiveData<ListingsViewState>
) : ViewModel() {

    init {
        disposables.add(categoryNumberObservable
                .flatMap { searchUseCase.search(it) }
                .subscribe({ viewState.value = it }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory @Inject constructor(
            private val categoryNumberObservable: Observable<String>,
            private val searchUseCase: SearchUseCase,
            private val disposables: CompositeDisposable,
            private val viewState: MutableLiveData<ListingsViewState>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                ListingsViewModel(categoryNumberObservable, searchUseCase, disposables, viewState) as T
    }
}