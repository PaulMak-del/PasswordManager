package com.example.passwordmanager.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Query("select * from password")
    fun getPasswords() : Flow<List<Password>>

    @Query("select * from password where favorite == true")
    suspend fun getFavoritePassword() : List<Password>

    @Insert
    suspend fun insert(password: Password) : Long

    @Delete
    suspend fun delete(password: Password) : Int

    @Update
    suspend fun update(password: Password) : Int

    @Query("delete from password")
    suspend fun deleteAll() : Int
}