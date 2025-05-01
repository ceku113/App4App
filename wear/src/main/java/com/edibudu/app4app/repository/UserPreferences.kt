package com.edibudu.app4app.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.edibudu.app4app.datastore.createDataStore
import com.edibudu.app4app.datastore.TODAY_COUNT
import com.edibudu.app4app.datastore.LAST_RESET_DATE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class UserPrefsRepository(context: Context) {
    // use the new factory-based DataStore
    private val dataStore = createDataStore(context)

    // Flow of today’s count, lazy-reset if date changed
    val todayCountFlow: Flow<Int> = dataStore.data
        .map { prefs ->
            val last = prefs[LAST_RESET_DATE]
            if (last != LocalDate.now().toString()) 0 else (prefs[TODAY_COUNT] ?: 0)
        }

    // increment, with reset-if-new-day
    suspend fun incrementCount() {
        val today = LocalDate.now().toString()
        dataStore.edit { prefs ->
            if (prefs[LAST_RESET_DATE] != today) {
                prefs.remove(TODAY_COUNT)
                prefs[LAST_RESET_DATE] = today
            }
            prefs[TODAY_COUNT] = (prefs[TODAY_COUNT] ?: 0) + 1
        }
    }

    // decrement, with same lazy-reset
    suspend fun decrementCount() {
        val today = LocalDate.now().toString()
        dataStore.edit { prefs ->
            if (prefs[LAST_RESET_DATE] != today) {
                prefs.remove(TODAY_COUNT)
                prefs[LAST_RESET_DATE] = today
            }
            val current = prefs[TODAY_COUNT] ?: 0
            prefs[TODAY_COUNT] = if (current > 0) current - 1 else 0
        }
    }

    // clear at midnight
    suspend fun clearCount() {
        dataStore.edit { prefs ->
            prefs.remove(TODAY_COUNT)
            prefs[LAST_RESET_DATE] = LocalDate.now().toString()
        }
    }
}
