package com.edibudu.app4app.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edibudu.app4app.dao.SmokeDao
import com.edibudu.app4app.repository.entities.SmokeEntity

// wear/src/main/java/com/example/app4app/wear/data/database/WearDatabase.kt
@Database(
    entities = [SmokeEntity::class],
    version = 2,
    exportSchema = false
)
abstract class SmokeRepository : RoomDatabase() {
    abstract fun wearDao(): SmokeDao

    companion object {
        @Volatile
        private var INSTANCE: SmokeRepository? = null

        fun getDatabase(context: Context): SmokeRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SmokeRepository::class.java,
                    "wear_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}