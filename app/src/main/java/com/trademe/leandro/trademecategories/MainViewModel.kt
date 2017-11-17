package com.trademe.leandro.trademecategories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.trademe.leandro.trademecategories.data.Category
import dagger.android.DispatchingAndroidInjector
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Leandro on 13/11/2017.
 */
class MainViewModel(
        val fragmentInjector: DispatchingAndroidInjector<Fragment>,
        private val categoryObserver: Observer<Category>,
        categoryObservable: Observable<Category>,
        val breadcrumb: MutableLiveData<List<Category>>
) : ViewModel() {
    private val disposables = CompositeDisposable()

    init {
        disposables.add(categoryObservable
                .scan(emptyList<Category>(), { categories, category ->
                    when {
                        categories.contains(category) -> categories.subList(0, categories.indexOf(category) + 1)
                        else -> categories.filter { !it.isLeaf }.plus(category)
                    }
                })
                .subscribe({ breadcrumb.value = it })
        )
    }

    fun onBreadcrumbItemClicked(it: Category) {
        categoryObserver.onNext(it)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    class Factory @Inject constructor(
            private val fragmentInjector: DispatchingAndroidInjector<Fragment>,
            private val observer: Observer<Category>,
            private val observable: Observable<Category>,
            private val breadcrumb: MutableLiveData<List<Category>>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = MainViewModel(
                fragmentInjector,
                observer,
                observable,
                breadcrumb
        ) as T
    }
}