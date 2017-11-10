package com.trademe.leandro.trademecategories

import com.trademe.leandro.trademecategories.data.Category
import com.trademe.leandro.trademecategories.data.SearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Leandro on 10/11/2017.
 */
interface TradeMeService {
    @GET("v1/Categories/0.json")
    fun getCategories(): Single<Category>

    @GET("v1/Search/General.json")
    fun search(
            @Query("category") category: String = "0",
            @Query("rows") rows: Int = 20
    ): Single<SearchResult>
}