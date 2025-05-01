package com.edibudu.app4app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_counts")
data class DailyCount(
    @PrimaryKey val date: String,    // e.g. "2025-05-01"
    val count: Int
)