package com.mihahoni.topnews.di

import com.mihahoni.topnews.data.dataSource.BaseDataSource
import com.mihahoni.topnews.data.dataSource.LocalDataSource
import com.mihahoni.topnews.data.dataSource.RemoteDataSource
import com.mihahoni.topnews.data.db.TopNewsDatabase
import com.mihahoni.topnews.data.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteDataSourceQualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalDataSourceQualifier

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteDataSourceQualifier
    @Provides
    fun provideRemoteDataSource(newsService: NewsService): BaseDataSource =
        RemoteDataSource(newsService)

    @Singleton
    @LocalDataSourceQualifier
    @Provides
    fun provideLocalDataSource(database: TopNewsDatabase): BaseDataSource =
        LocalDataSource(database.sourceDao(), database.articleDao())
}