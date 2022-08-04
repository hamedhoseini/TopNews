package com.mihahoni.topnews.data.repository

import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.model.NewsResponse
import com.mihahoni.topnews.data.model.NewsSourceResponse
import com.mihahoni.topnews.data.service.NewsService
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val newsService: NewsService) : NewsRepository {
    override suspend fun getServiceFromRemote(): Result<NewsSourceResponse> {

        return try {
            Result.Success(data = newsService.getSources())
        } catch (e: Exception) {
            Result.Error(exception = e)
        }
    }

    override suspend fun getNewsBySourceId(sourceId: String): Result<NewsResponse> {
        return try {
            Result.Success(data = newsService.getNewsBySourceId(sourceId))
        } catch (e: Exception) {
            Result.Error(exception = e)
        }
    }
}