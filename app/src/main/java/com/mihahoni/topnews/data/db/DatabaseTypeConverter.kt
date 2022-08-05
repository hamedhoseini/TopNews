package com.mihahoni.topnews.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mihahoni.topnews.data.model.SourceItem
import java.util.*

object DatabaseTypeConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun sourceToString(app: SourceItem): String = Gson().toJson(app)

    @TypeConverter
    fun stringToSource(string: String): SourceItem = Gson().fromJson(string, SourceItem::class.java)

}