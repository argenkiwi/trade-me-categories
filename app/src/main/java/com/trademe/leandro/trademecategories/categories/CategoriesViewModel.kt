package com.trademe.leandro.trademecategories.categories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.trademe.leandro.trademecategories.TradeMeService
import com.trademe.leandro.trademecategories.data.Category
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Leandro on 10/11/2017.
 */
class CategoriesViewModel(
        private val categoryObserver: Observer<Category>,
        categoryObservable: Observable<Category>,
        service: TradeMeService,
        private val disposables: CompositeDisposable,
        val viewState: MutableLiveData<CategoriesViewState>
) : ViewModel() {

    init {
        viewState.value = Loading
        disposables.add(service.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onCategorySelected(it)
                }, {
                    viewState.value = Failure(it)
                }));

        disposables.add(categoryObservable
                .filter({ !it.isLeaf })
                .subscribe({ viewState.value = Success(it) }))
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
            private val service: TradeMeService,
            private val disposables: CompositeDisposable,
            private val viewState: MutableLiveData<CategoriesViewState>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = CategoriesViewModel(
                categoryObserver,
                categoryObservable,
                service,
                disposables,
                viewState
        ) as T
    }
}