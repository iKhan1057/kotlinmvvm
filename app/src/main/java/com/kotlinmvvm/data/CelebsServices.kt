package com.kotlinmvvm.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CelebsServices {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    private val api: CelebsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CelebsApi::class.java)

    fun getCelebs(): Single<List<CelebsModel>> {
        return api.getCelebsDetails()
    }
}