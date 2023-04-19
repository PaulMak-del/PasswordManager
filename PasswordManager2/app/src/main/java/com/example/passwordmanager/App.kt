package com.example.passwordmanager

import android.app.Application
import com.example.passwordmanager.model.PasswordDatabase
import com.example.passwordmanager.model.PasswordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { PasswordDatabase.getDataBase(this, applicationScope)}
    val repository by lazy { PasswordRepository(database.passwordDao()) }
}