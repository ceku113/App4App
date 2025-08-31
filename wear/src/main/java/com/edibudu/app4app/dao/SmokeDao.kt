package com.edibudu.app4app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edibudu.app4app.repository.entities.SmokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SmokeDao {
    @Query("SELECT * FROM smoke ORDER BY timestamp DESC")
    fun getAllItems(): Flow<List<SmokeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: SmokeEntity)

    @Delete
    suspend fun deleteItem(item: SmokeEntity)

    @Query("SELECT * FROM smoke WHERE timestamp BETWEEN :start AND :end ORDER BY timestamp DESC")
    fun getItemsBetween(start: Long, end: Long): Flow<List<SmokeEntity>>


    @Query("""
    DELETE FROM smoke 
     WHERE id = (
       SELECT id 
         FROM smoke             
        ORDER BY timestamp DESC 
        LIMIT 1
     )
  """)
    suspend fun deleteLatest()
}