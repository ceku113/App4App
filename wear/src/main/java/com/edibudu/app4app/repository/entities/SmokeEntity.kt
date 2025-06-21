package com.edibudu.app4app.repository.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smoke")
data class SmokeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Long
)