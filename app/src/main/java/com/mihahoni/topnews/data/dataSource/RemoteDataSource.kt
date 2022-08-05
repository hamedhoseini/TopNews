package com.mihahoni.topnews.data.dataSource

import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import com.mihahoni.topnews.data.service.NewsService
import java.util.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val newsService: NewsService) : BaseDataSource {

    override suspend fun getNewsBySource(sourceId: String): Result<List<ArticleItem>> {
        try {
            return Result.Success(data = newsService.getNewsBySourceId(sourceId).articleList)

        } catch (e: Exception) {
            return Result.Error(exception = e)
        }
    }

    override suspend fun insertArticles(articleList: List<ArticleItem>) {

    }

    override suspend fun getSources(): Result<List<SourceItem>> {
        try {
            return Result.Success(data = newsService.getSources().sourcesList)

        } catch (e: Exception) {
            return Result.Error(exception = e)
        }
    }

    override suspend fun insertSources(sourcesList: List<SourceItem>) {

    }

    override suspend fun getSourceLastUpdateTime(sourceId: String): Date? {
        return Date()
    }

    override suspend fun setSourceLastUpdateTime(sourceId: String, date: Date) {

    }
}