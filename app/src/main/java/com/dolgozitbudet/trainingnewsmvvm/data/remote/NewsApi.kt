package com.dolgozitbudet.trainingnewsmvvm.data.remote

import com.dolgozitbudet.trainingnewsmvvm.data.remote.dto.NewsResponse
import com.dolgozitbudet.trainingnewsmvvm.util.Constants.APP_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = APP_KEY,
    ): NewsResponse
}