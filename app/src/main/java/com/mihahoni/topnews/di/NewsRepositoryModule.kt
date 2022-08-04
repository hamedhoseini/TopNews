package com.mihahoni.topnews.di

import com.mihahoni.topnews.data.repository.NewsRepository
import com.mihahoni.topnews.data.repository.NewsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRepositoryModule {

    @Binds
    abstract fun bindAnalyticsService(
        newsRepositoryImp: NewsRepositoryImp
    ): NewsRepository
}