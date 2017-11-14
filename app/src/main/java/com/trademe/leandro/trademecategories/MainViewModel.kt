package com.trademe.leandro.trademecategories

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.trademe.leandro.trademecategories.data.Category
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

/**
 * Created by Leandro on 13/11/2017.
 */
class MainViewModel : ViewModel() {
    val category: Subject<Category> = BehaviorSubject.create()
    val breadcrumb = MutableLiveData<List<Category>>()

    private val disposables = CompositeDisposable()

    init {
        disposables.add(category.subscribe({
            val categories = breadcrumb.value;
            breadcrumb.value = when {
                categories == null -> arrayListOf(it)
                categories.contains(it) -> categories.subList(0, categories.indexOf(it) + 1)
                else -> categories.filter { !it.isLeaf }.plus(it)
            }
        }))
    }

    fun onBreadcrumbItemClicked(it: Category) {
        category.onNext(it)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}