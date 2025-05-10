package com.edibudu.app4app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.edibudu.app4app.data.repository.SmokeRepository
import com.edibudu.app4app.repository.entities.WearEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WearViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = SmokeRepository.getDatabase(application).wearDao()

    val allItems: Flow<List<WearEntity>> = dao.getAllItems()

    fun insert(content: String) {
        viewModelScope.launch {
            dao.insertItem(WearEntity(content = content, timestamp = System.currentTimeMillis()))
        }
    }
}
