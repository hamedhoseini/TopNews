package com.mihahoni.topnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem
import java.util.*

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<ArticleItem>

    @Insert
    fun insertArticles(articles: List<ArticleItem>): List<Long>

}