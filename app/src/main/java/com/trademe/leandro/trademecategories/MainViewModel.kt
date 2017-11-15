package com.trademe.leandro.trademecategories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.trademe.leandro.trademecategories.data.Category
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Leandro on 13/11/2017.
 */
class MainViewModel(
        val categoryObserver: Observer<Category>,
        val categoryObservable: Observable<Category>
) : ViewModel() {
    val breadcrumb = MutableLiveData<List<Category>>()

    private val disposables = CompositeDisposable()

    init {
        disposables.add(categoryObservable.subscribe({
            val categories = breadcrumb.value;
            breadcrumb.value = when {
                categories == null -> arrayListOf(it)
                categories.contains(it) -> categories.subList(0, categories.indexOf(it) + 1)
                else -> categories.filter { !it.isLeaf }.plus(it)
            }
        }))
    }

    fun onBreadcrumbItemClicked(it: Category) {
        categoryObserver.onNext(it)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory(
            private val observer: Observer<Category>,
            private val observable: Observable<Category>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                MainViewModel(observer, observable) as T
    }
}