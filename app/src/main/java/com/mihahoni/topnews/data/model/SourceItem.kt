package com.mihahoni.topnews.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "sources")
data class SourceItem(
    @PrimaryKey(autoGenerate = true)
    val row: Int,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "category")
    val category: String?,
    @ColumnInfo(name = "language")
    val language: String?,
    @ColumnInfo(name = "country")
    val country: String?,
    @ColumnInfo(name = "last_update_time")
    val last_Update_Time: Date?
) : Serializable

