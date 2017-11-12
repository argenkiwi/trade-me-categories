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
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Leandro on 10/11/2017.
 */
class CategoriesViewModel(
        private val categoryObserver: Observer<Category>,
        categoryObservable: Observable<Category>,
        service: TradeMeService
) : ViewModel() {
    private val disposables = CompositeDisposable()

    val category: MutableLiveData<Category> = MutableLiveData<Category>()

    init {
        disposables.add(service.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { onCategorySelected(it) }));

        disposables.add(categoryObservable.subscribe(Consumer { category.value = it }))
    }

    fun onCategorySelected(category: Category) {
        categoryObserver.onNext(category)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory(
            private val categoryObserver: Observer<Category>,
            private val categoryObservable: Observable<Category>,
            private val service: TradeMeService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CategoriesViewModel(categoryObserver, categoryObservable, service) as T
        }
    }
}