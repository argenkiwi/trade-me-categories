package com.trademe.leandro.trademecategories

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by leandro on 22/07/16.
 */
@Module
object ServicesModule {

    @JvmStatic
    @Provides
    @Singleton
    fun tradeMeService(
            retrofit: Retrofit
    ): TradeMeService = retrofit.create(TradeMeService::class.java)

    @JvmStatic
    @Provides
    @Singleton
    fun gson() = GsonBuilder().create()

    @JvmStatic
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

    @JvmStatic
    @Provides
    fun retrofit(
            client: OkHttpClient,
            gson: Gson
    ): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @JvmStatic
    @Provides
    fun okHttpClient(
            interceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
}
