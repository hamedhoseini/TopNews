package com.mihahoni.topnews.data.service

import com.mihahoni.topnews.data.model.ArticleResponse
import com.mihahoni.topnews.data.model.NewsSourceResponse
import com.mihahoni.topnews.utils.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("X-Api-Key:" + Constants.API_KEY)
    @GET("/v2/top-headlines/sources")
    fun getSources(
    ): Observable<NewsSourceResponse>

    @Headers("X-Api-Key:" + Constants.API_KEY)
    @GET("/v2/top-headlines")
    fun getNewsBySourceId(@Query("sources") sourceId: String): Observable<ArticleResponse>
}