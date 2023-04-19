package com.example.passwordmanager.model

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PasswordRepository(private val passwordDao: PasswordDao) {

    val allPasswords: Flow<List<Password>> = passwordDao.getPasswords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(password: Password) {
        passwordDao.insert(password)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(password: Password) {
        passwordDao.delete(password)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(password: Password) {
        passwordDao.update(password)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateById(id: Int, name: String, login: String, password: String) {
        passwordDao.updateById(id, name, login, password)
    }
}