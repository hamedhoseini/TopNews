package com.mihahoni.topnews.data.repository

import com.mihahoni.topnews.data.service.NewsService
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor (private val newsService: NewsService) :NewsRepository {
    override suspend fun getServiceFromRemote() {
        newsService.getSources()
    }
}