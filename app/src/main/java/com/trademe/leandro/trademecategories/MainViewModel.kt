package com.trademe.leandro.trademecategories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import ar.soflete.cycler.ReactiveViewModel
import com.trademe.leandro.trademecategories.data.Category
import dagger.android.DispatchingAndroidInjector
import io.reactivex.Observable
import io.reactivex.Observer
import javax.inject.Inject

/**
 * Created by Leandro on 13/11/2017.
 */
class MainViewModel(
        val fragmentInjector: DispatchingAndroidInjector<Fragment>,
        categoryObservable: Observable<Category>,
        categoryObserver: Observer<Category>
) : ReactiveViewModel<List<Category>, Category>(emptyList<Category>(), { categories, category ->
    when {
        categories.contains(category) -> categories.subList(0, categories.indexOf(category) + 1)
        else -> categories.filter { !it.isLeaf }.plus(category)
    }
}) {

    private val disposable = events.distinctUntilChanged().subscribe { categoryObserver.onNext(it) }

    init {
        subscribe(categoryObservable)
    }

    override fun onCleared() {
        disposable.dispose()
    }

    class Factory @Inject constructor(
            private val fragmentInjector: DispatchingAndroidInjector<Fragment>,
            private val categoryObservable: Observable<Category>,
            private val categoryObserver: Observer<Category>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
                MainViewModel(fragmentInjector, categoryObservable, categoryObserver) as T
    }
}