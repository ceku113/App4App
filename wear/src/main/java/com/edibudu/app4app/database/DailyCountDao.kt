package com.edibudu.app4app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyCountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(day: DailyCount)

    @Query("""
    SELECT * FROM daily_counts 
    WHERE CAST(strftime('%s', date) AS INTEGER) 
      BETWEEN strftime('%s','now','-6 days') 
          AND strftime('%s','now')
    ORDER BY date ASC
  """)
    fun last7Days(): Flow<List<DailyCount>>
}