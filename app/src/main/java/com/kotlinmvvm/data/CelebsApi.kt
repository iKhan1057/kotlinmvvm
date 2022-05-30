package com.kotlinmvvm.data

import io.reactivex.Single
import retrofit2.http.GET

interface CelebsApi {
    @GET("iKhan1057/kotlinmvvm/main/celebs.json")
    fun getCelebsDetails(): Single<List<CelebsModel>>
}