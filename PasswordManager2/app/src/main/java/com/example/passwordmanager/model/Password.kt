package com.example.passwordmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password")
data class Password (
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "password") var password: String,
) {

    @PrimaryKey(autoGenerate = true) var id: Long = 0
    @ColumnInfo(name = "favorite") var isFavorite: Boolean = false
}