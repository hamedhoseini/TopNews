package com.mihahoni.topnews.data.dataSource

import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import java.util.*

interface BaseDataSource {

    suspend fun getSources(): Result<List<SourceItem>>
    suspend fun insertSources(sourcesList: List<SourceItem>)
    suspend fun getSourceLastUpdateTime(sourceId: String): Date?
    suspend fun setSourceLastUpdateTime(sourceId: String, date: Date)
    suspend fun getNewsBySource(sourceId: String): Result<List<ArticleItem>>
    suspend fun insertArticles(articleList: List<ArticleItem>)
}
