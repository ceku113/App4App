package com.edibudu.app4app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.edibudu.app4app.repository.SmokeRepository
import com.edibudu.app4app.repository.entities.SmokeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit
import android.hardware.Sensor

import android.hardware.SensorManager

class WearViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = SmokeRepository.getDatabase(app).wearDao()
    private val sensorManager = app.getSystemService(SensorManager::class.java)
    private val accSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        ?: sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val gyroSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    val allItems: Flow<List<SmokeEntity>> = dao.getAllItems()

    fun insert() {
        viewModelScope.launch {
            dao.insertItem(SmokeEntity(timestamp = System.currentTimeMillis()))
        }
    }

    // --- DEBUG: dump everything in logcat ---
    /** Returns only the records whose `timestamp` falls in [startOfDay, endOfDay). */
    fun todayItems(): Flow<List<SmokeEntity>> {
        val cal = Calendar.getInstance().apply {
            // midnight today
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val start = cal.timeInMillis
        val end = start + TimeUnit.DAYS.toMillis(1)
        return dao.getItemsBetween(start, end)
    }
    // --- DEBUG: dump everything in logcat ---

    fun deleteLatest() = viewModelScope.launch {
        dao.deleteLatest()
    }
}
