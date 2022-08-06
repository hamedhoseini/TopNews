package com.mihahoni.topnews.data.dataSource

import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import com.mihahoni.topnews.data.service.NewsService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val newsService: NewsService) : BaseDataSource {

    override fun getNewsBySource(sourceId: String): Observable<List<ArticleItem>> {
        return newsService.getNewsBySourceId(sourceId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map { it.articleList }
    }

    override fun insertArticles(articleList: List<ArticleItem>) {

    }

    override fun getSources(): Observable<List<SourceItem>> {

        return newsService.getSources().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map { it.sourcesList }

    }

    override fun insertSources(sourcesList: List<SourceItem>) {}

    override fun getSourceLastUpdateTime(sourceId: String): Single<Date?> {
        return Single.never()
    }

    override fun setSourceLastUpdateTime(sourceId: String, date: Date) {

    }
}