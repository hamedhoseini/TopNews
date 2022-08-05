package com.mihahoni.topnews.data.dataSource

import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.db.ArticleDao
import com.mihahoni.topnews.data.db.SourceDao
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sourceDao: SourceDao,
    private val articleDao: ArticleDao
) : BaseDataSource {
    override suspend fun getNewsBySource(sourceId: String): Result<List<ArticleItem>> {
        return Result.Success(data = articleDao.getAllArticles())
    }

    override suspend fun insertArticles(articleList: List<ArticleItem>) {
        articleDao.insertArticles(articleList)
    }

    override suspend fun getSources(): Result<List<SourceItem>> {
        return Result.Success(data = sourceDao.getAllSources())
    }

    override suspend fun insertSources(sourcesList: List<SourceItem>) {
        sourceDao.insertSources(sourcesList)
    }

    override suspend fun getSourceLastUpdateTime(sourceId: String): Date? {
        return sourceDao.getSourceLastUpdateTime(sourceId)
    }

    override suspend fun setSourceLastUpdateTime(sourceId: String, date: Date) {
        sourceDao.updateSourceNewsUpdateTime(sourceId,date)
    }
}