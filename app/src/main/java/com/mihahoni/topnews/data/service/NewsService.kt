package com.mihahoni.topnews.data.service

import com.mihahoni.topnews.data.model.NewsResponse
import com.mihahoni.topnews.data.model.NewsSourceResponse
import com.mihahoni.topnews.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("X-Api-Key:" + Constants.API_KEY)
    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
    ): NewsSourceResponse

    @Headers("X-Api-Key:" + Constants.API_KEY)
    @GET("/v2/top-headlines")
    suspend fun getNewsBySourceId(@Query("sources") sourceId: String): NewsResponse
}