package com.trademe.leandro.trademecategories

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Leandro on 15/11/2017.
 */
class SearchUseCase(private val service: TradeMeService) {
    fun search(categoryNumber: String) = service
            .search(categoryNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}