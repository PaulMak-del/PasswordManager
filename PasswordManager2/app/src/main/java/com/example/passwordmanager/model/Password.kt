package com.example.passwordmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "password",
    foreignKeys = arrayOf(ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("uid"),
        onDelete = ForeignKey.CASCADE,
    ))
    )
data class Password (
    var name: String,
    var login: String,
    var password: String,
    val uid: String,
) {

    @PrimaryKey(autoGenerate = true) var id: Long = 0
    @ColumnInfo(name = "favorite") var isFavorite: Boolean = false
}

@Entity(tableName = "user")
data class User (
    @PrimaryKey
    val uid: String,
        )
