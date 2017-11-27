package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ar.soflete.cycler.ReactiveViewModel
import com.trademe.leandro.trademecategories.SearchUseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Leandro on 10/11/2017.
 */
class ListingsViewModel(
        categoryNumberObservable: Observable<String>,
        searchUseCase: SearchUseCase
) : ReactiveViewModel<ListingsState, ListingsEvent>(
        ListingsState.Loading,
        { _, event ->
            when (event) {
                is ListingsEvent.LoadListings -> ListingsState.Loading
                is ListingsEvent.LoadListingsError -> ListingsState.Failure(event.error)
                is ListingsEvent.ListingsLoaded -> when {
                    event.searchResult.totalCount > 0 -> ListingsState.Success(event.searchResult)
                    else -> ListingsState.Empty
                }
            }
        }
) {

    init {
        subscribe(categoryNumberObservable.distinctUntilChanged()
                .flatMap { searchUseCase.search(it) }
                .map { ListingsEvent.ListingsLoaded(it) as ListingsEvent }
                .onErrorReturn { ListingsEvent.LoadListingsError(it) })
    }

    class Factory @Inject constructor(
            private val categoryNumberObservable: Observable<String>,
            private val searchUseCase: SearchUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                ListingsViewModel(categoryNumberObservable, searchUseCase) as T
    }
}