package com.mihahoni.topnews.data.dataSource

import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

interface BaseDataSource {

    fun getSources(): Observable<List<SourceItem>>
    fun insertSources(sourcesList: List<SourceItem>)
    fun getSourceLastUpdateTime(sourceId: String): Single<Date?>
    fun setSourceLastUpdateTime(sourceId: String, date: Date)
    fun getNewsBySource(sourceId: String): Observable<List<ArticleItem>>
    fun insertArticles(articleList: List<ArticleItem>)
}
