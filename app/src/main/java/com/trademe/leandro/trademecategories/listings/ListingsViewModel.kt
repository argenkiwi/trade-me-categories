package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.trademe.leandro.trademecategories.TradeMeService
import com.trademe.leandro.trademecategories.data.SearchResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Leandro on 10/11/2017.
 */
class ListingsViewModel(
        categoryNumberObservable: Observable<String>,
        service: TradeMeService
) : ViewModel() {
    private val disposables = CompositeDisposable()

    val searchResult: MutableLiveData<SearchResult> = MutableLiveData<SearchResult>()

    init {
        disposables.add(categoryNumberObservable.flatMapSingle {
            service.search(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }.subscribe(Consumer { searchResult.value = it }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory(
            private val categoryNumberObservable: Observable<String>,
            private val service: TradeMeService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListingsViewModel(categoryNumberObservable, service) as T
        }
    }
}