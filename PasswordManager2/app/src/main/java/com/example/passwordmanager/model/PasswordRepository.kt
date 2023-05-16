package com.example.passwordmanager.model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PasswordRepository(
    private val passwordDao: PasswordDao,
    ) {
    fun getUsersPasswords(userID: String) : Flow<List<Password>> = passwordDao.getUserPasswords(userID)
    fun getFavoritePasswords(uid: String) : Flow<List<Password>> = passwordDao.getFavoritePasswords(uid)
    @WorkerThread
    suspend fun insertPassword(password: Password) {
        passwordDao.insertPassword(password)
    }
    @WorkerThread
    suspend fun insertUser(user: User) {
        passwordDao.insertUser(user)
    }
    @WorkerThread
    suspend fun deletePassword(password: Password) {
        passwordDao.deletePassword(password)
    }
    @WorkerThread
    suspend fun updatePassword(password: Password) {
        passwordDao.updatePassword(password)
    }
    @WorkerThread
    suspend fun updatePasswordById(id: Int, name: String, login: String, password: String) {
        passwordDao.updatePasswordById(id, name, login, password)
    }
}