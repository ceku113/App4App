package com.edibudu.app4app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edibudu.app4app.repository.entities.WearEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SmokeDao {
    @Query("SELECT * FROM wear_items ORDER BY timestamp DESC")
    fun getAllItems(): Flow<List<WearEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: WearEntity)

    @Delete
    suspend fun deleteItem(item: WearEntity)
}