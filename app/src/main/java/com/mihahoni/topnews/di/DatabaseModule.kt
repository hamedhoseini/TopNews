package com.mihahoni.topnews.di

import android.content.Context
import androidx.room.Room
import com.mihahoni.topnews.data.db.ArticleDao
import com.mihahoni.topnews.data.db.SourceDao
import com.mihahoni.topnews.data.db.TopNewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TopNewsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TopNewsDatabase::class.java,
            "topNews.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSourcesDao(db: TopNewsDatabase): SourceDao = db.sourceDao()

    @Singleton
    @Provides
    fun provideArticlesDao(db: TopNewsDatabase): ArticleDao = db.articleDao()
}