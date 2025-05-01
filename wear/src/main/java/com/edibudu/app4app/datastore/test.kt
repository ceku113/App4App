// wear/src/.../datastore/Test.kt
package com.edibudu.app4app.datastore

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

// these two lines must compile without error:
val testKey1 = intPreferencesKey("test1")
val testKey2 = stringPreferencesKey("test2")
val testKey = intPreferencesKey("test")
