package com.edibudu.app4app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edibudu.app4app.repository.UserPrefsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repo: UserPrefsRepository): ViewModel() {
    val count = repo.todayCountFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)        // expose as StateFlow :contentReference[oaicite:8]{index=8}

    fun onSmoke() {
        viewModelScope.launch { repo.incrementCount() }            // increment via DataStore :contentReference[oaicite:9]{index=9}
    }

    fun decrement() {
        viewModelScope.launch { repo.decrementCount() }            // optional: implement decrement in repo
    }


}