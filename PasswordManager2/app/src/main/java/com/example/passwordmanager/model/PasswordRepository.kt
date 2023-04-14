package com.example.passwordmanager.model

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
}