package com.mihahoni.topnews.data.repository

import com.mihahoni.topnews.data.Result
import com.mihahoni.topnews.data.dataSource.BaseDataSource
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import java.util.*
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val remoteDataSource: BaseDataSource,
    private val localDataSource: BaseDataSource,
) : NewsRepository {
    override suspend fun getSources(): Result<List<SourceItem>> {

        try {
            updateSourceFromRemoteDataSource()
        } catch (ex: Exception) {
            return Result.Error(ex)
        }
        return localDataSource.getSources()
    }

    override suspend fun getArticleBySourceId(sourceId: String): Result<List<ArticleItem>> {
        if (shouldFetchArticleFromRemote(sourceId)) {
            try {
                updateArticlesFromRemoteDataSource(sourceId)
                updateSourceLastUpdateTime(sourceId)
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }
        return localDataSource.getNewsBySource(sourceId)
    }

    private suspend fun updateSourceLastUpdateTime(sourceId: String) {
        localDataSource.setSourceLastUpdateTime(sourceId, Date())
    }

    private suspend fun updateSourceFromRemoteDataSource() {
        val remoteSource = remoteDataSource.getSources()

        if (remoteSource is Result.Success) {
            localDataSource.insertSources(remoteSource.data)
        }
//        else if (remoteSource is Result.Error) {
//            throw remoteSource.exception
//        }
    }

    private suspend fun updateArticlesFromRemoteDataSource(sourceId: String) {
        val remoteArticles = remoteDataSource.getNewsBySource(sourceId)

        if (remoteArticles is Result.Success) {
            localDataSource.insertArticles(remoteArticles.data)
        }
//        else if (remoteSource is Result.Error) {
//            throw remoteSource.exception
//        }
    }

    private suspend fun shouldFetchArticleFromRemote(sourceId: String): Boolean {
        val sourceLastUpdateTime = localDataSource.getSourceLastUpdateTime(sourceId)
        val last15Minute = Date(System.currentTimeMillis() - (15 * 60 * 1000))

        return sourceLastUpdateTime == null || sourceLastUpdateTime.before(last15Minute)
    }

}