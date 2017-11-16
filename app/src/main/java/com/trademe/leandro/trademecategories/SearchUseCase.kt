package com.trademe.leandro.trademecategories

import com.trademe.leandro.trademecategories.listings.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Leandro on 15/11/2017.
 */
class SearchUseCase(private val service: TradeMeService) {
    fun search(categoryNumber: String): Observable<ListingsViewState> = service
            .search(categoryNumber)
            .map {
                when {
                    it.totalCount > 0 -> Success(it)
                    else -> Empty
                }
            }
            .onErrorReturn { Failure(it) }
            .startWith(Loading)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}