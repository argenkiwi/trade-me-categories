package com.trademe.leandro.trademecategories

import com.trademe.leandro.trademecategories.data.Category
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Leandro on 10/11/2017.
 */
interface TradeMeService {
    @GET("v1/Categories/0.json")
    fun getCategories(): Single<Category>
}