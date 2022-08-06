package com.mihahoni.topnews.data.dataSource

import com.mihahoni.topnews.data.db.ArticleDao
import com.mihahoni.topnews.data.db.SourceDao
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sourceDao: SourceDao,
    private val articleDao: ArticleDao
) : BaseDataSource {
    override fun getNewsBySource(sourceId: String): Observable<List<ArticleItem>> {
        return articleDao.getArticlesBySource(sourceId)
    }

    override fun insertArticles(articleList: List<ArticleItem>) {
        articleDao.insertArticles(articleList)
    }

    override fun getSources(): Observable<List<SourceItem>> {
        return sourceDao.getAllSources()
    }

    override fun insertSources(sourcesList: List<SourceItem>) {
        sourceDao.insertSources(sourcesList)
    }

    override fun getSourceLastUpdateTime(sourceId: String): Single<Date?> {
        return sourceDao.getSourceLastUpdateTime(sourceId)
    }

    override fun setSourceLastUpdateTime(sourceId: String, date: Date) {
        sourceDao.updateSourceNewsUpdateTime(sourceId, date)
    }
}