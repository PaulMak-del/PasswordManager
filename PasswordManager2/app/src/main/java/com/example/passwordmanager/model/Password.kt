package com.example.passwordmanager.model

data class Password (
    val id: Long,
    val name: String,
    val password: String,
    var isFavorite: Boolean,
)