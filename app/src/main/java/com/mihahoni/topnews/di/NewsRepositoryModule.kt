package com.mihahoni.topnews.di

import com.mihahoni.topnews.data.dataSource.BaseDataSource
import com.mihahoni.topnews.data.repository.NewsRepository
import com.mihahoni.topnews.data.repository.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsRepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        @RemoteDataSourceQualifier remoteDataSource: BaseDataSource,
        @LocalDataSourceQualifier localDataSource: BaseDataSource
    ): NewsRepository {
        return NewsRepositoryImp(remoteDataSource, localDataSource)
    }
}