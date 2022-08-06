package com.mihahoni.topnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mihahoni.topnews.data.model.ArticleItem
import io.reactivex.Observable

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Observable<List<ArticleItem>>

    @Query("SELECT * FROM articles WHERE source_id =:sourceId")
    fun getArticlesBySource(sourceId: String): Observable<List<ArticleItem>>

    @Insert
    fun insertArticles(articles: List<ArticleItem>): List<Long>

}