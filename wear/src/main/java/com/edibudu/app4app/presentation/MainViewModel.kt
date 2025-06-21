package com.edibudu.app4app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.edibudu.app4app.repository.SmokeRepository
import com.edibudu.app4app.repository.entities.SmokeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WearViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = SmokeRepository.getDatabase(application).wearDao()

    val allItems: Flow<List<SmokeEntity>> = dao.getAllItems()

    fun insert(count: Int) {
        viewModelScope.launch {
            dao.insertItem(SmokeEntity(count = count, timestamp = System.currentTimeMillis()))
        }
    }
}
