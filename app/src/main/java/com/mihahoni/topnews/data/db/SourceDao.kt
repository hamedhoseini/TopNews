package com.mihahoni.topnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mihahoni.topnews.data.model.SourceItem
import java.util.*

@Dao
interface SourceDao {

    @Query("SELECT * FROM sources")
    fun getAllSources(): List<SourceItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSources(sources: List<SourceItem>): List<Long>

    @Query("UPDATE sources SET last_update_time= :lastUpdateTime WHERE id =:sourceId")
    fun updateSourceNewsUpdateTime(sourceId:String, lastUpdateTime: Date): Int

    @Query("SELECT last_update_time FROM sources WHERE id =:sourceId")
    fun getSourceLastUpdateTime(sourceId:String): Date?
}