package com.trademe.leandro.trademecategories.listings

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.trademe.leandro.trademecategories.TradeMeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Leandro on 10/11/2017.
 */
class ListingsViewModel(
        service: TradeMeService
) : ViewModel() {
    init {
        service.search()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    Log.d(ListingsViewModel::class.java.simpleName, it.list.toString())
                })
    }

    class Factory(
            private val service: TradeMeService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ListingsViewModel(service) as T
        }
    }
}