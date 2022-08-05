package com.mihahoni.topnews.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mihahoni.topnews.data.model.ArticleItem
import com.mihahoni.topnews.data.model.SourceItem

@Database(entities = [SourceItem::class,ArticleItem::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseTypeConverter::class)
abstract class TopNewsDatabase : RoomDatabase() {

    abstract fun sourceDao(): SourceDao
    abstract fun articleDao(): ArticleDao
}