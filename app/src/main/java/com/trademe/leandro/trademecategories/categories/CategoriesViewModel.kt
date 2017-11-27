package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ar.soflete.cycler.ReactiveViewModel
import com.trademe.leandro.trademecategories.TradeMeService
import com.trademe.leandro.trademecategories.data.Category
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Leandro on 10/11/2017.
 */
class CategoriesViewModel(
        private val categoryObserver: Observer<Category>,
        categoryObservable: Observable<Category>,
        service: TradeMeService
) : ReactiveViewModel<CategoriesState, CategoriesEvent>(
        CategoriesState.Loading,
        { _, event ->
            when (event) {
                is CategoriesEvent.LoadCategories -> CategoriesState.Loading
                is CategoriesEvent.LoadCategoriesError -> CategoriesState.Failure(event.error)
                is CategoriesEvent.CategoriesLoaded -> CategoriesState.Success(event.category)
            }
        }
) {
    private val disposables = events.startWith(CategoriesEvent.LoadCategories)
            .filter { it is CategoriesEvent.LoadCategories }
            .flatMap {
                service.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe { onCategorySelected(it) }

    init {
        subscribe(categoryObservable.distinctUntilChanged()
                .filter { !it.isLeaf }
                .map { CategoriesEvent.CategoriesLoaded(it) })
    }

    fun onCategorySelected(category: Category) {
        categoryObserver.onNext(category)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory @Inject constructor(
            private val categoryObserver: Observer<Category>,
            private val categoryObservable: Observable<Category>,
            private val service: TradeMeService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = CategoriesViewModel(
                categoryObserver,
                categoryObservable,
                service
        ) as T
    }
}