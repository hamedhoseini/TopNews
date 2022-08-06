package com.mihahoni.topnews.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.mihahoni.topnews.data.dataSource.BaseDataSource
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val remoteDataSource: BaseDataSource,
    private val localDataSource: BaseDataSource,
) : NewsRepository {


    override fun getSources(): Observable<List<SourceItem>> {
        updateSourceFromRemoteDataSource()

        return localDataSource.getSources().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    override fun getArticleBySourceId(sourceId: String): Observable<List<ArticleItem>> {

        checkFetchArticleFromRemote(sourceId)
        return Observable.concatArrayEager(
            localDataSource.getNewsBySource(sourceId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()),
            updateArticlesFromRemoteDataSource(sourceId)

        )
    }

    private fun updateSourceLastUpdateTime(sourceId: String): Completable {
        return Completable.fromAction {
            localDataSource.setSourceLastUpdateTime(sourceId, Date())
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    @SuppressLint("CheckResult")
    private fun updateSourceFromRemoteDataSource() {
        remoteDataSource.getSources().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                insertNewsSourcesToLocal(it).subscribe()
            }
    }

    private fun insertNewsSourcesToLocal(sourcesList: List<SourceItem>): Completable {
        return Completable.fromAction {
            localDataSource.insertSources(sourcesList)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    @SuppressLint("CheckResult")
    private fun updateArticlesFromRemoteDataSource(sourceId: String): Observable<List<ArticleItem>> {
        return remoteDataSource.getNewsBySource(sourceId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnNext {
                insertArticlesToLocal(it).subscribe()
                updateSourceLastUpdateTime(sourceId).subscribe()
            }
    }

    private fun insertArticlesToLocal(articleList: List<ArticleItem>): Completable {
        return Completable.fromAction {
            localDataSource.insertArticles(articleList)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    private fun checkFetchArticleFromRemote(sourceId: String) {
        localDataSource.getSourceLastUpdateTime(sourceId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val last15Minute = Date(System.currentTimeMillis() - (15 * 60 * 1000))
                if (it == null || it.before(last15Minute)) {
                    updateArticlesFromRemoteDataSource(sourceId)
                }
            }, {
                updateArticlesFromRemoteDataSource(sourceId)
            })
    }

}