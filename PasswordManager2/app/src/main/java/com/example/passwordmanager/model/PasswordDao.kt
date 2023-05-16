package com.example.passwordmanager.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Query("select * from password where uid = :uid")
    fun getUserPasswords(uid: String) : Flow<List<Password>>
    @Query("select * from password where favorite = 1 and uid = :uid")
    fun getFavoritePasswords(uid: String) : Flow<List<Password>>
    @Query("update password set name = :name, password = :password, login = :login where id == :id")
    suspend fun updatePasswordById(id: Int, name: String, login: String, password: String) : Int
    @Insert
    suspend fun insertPassword(password: Password) : Long
    @Insert
    suspend fun insertUser(user: User) : Long
    @Delete
    suspend fun deletePassword(password: Password) : Int
    @Update
    suspend fun updatePassword(password: Password) : Int
    @Query("delete from password")
    suspend fun deleteAllPasswords() : Int
}