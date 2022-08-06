package com.mihahoni.topnews.data.repository

import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import io.reactivex.Observable

interface NewsRepository {
    fun getSources(): Observable<List<SourceItem>>
    fun getArticleBySourceId(sourceId: String): Observable<List<ArticleItem>>
}