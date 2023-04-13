package com.example.passwordmanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.model.PasswordListener
import com.example.passwordmanager.model.PasswordService

class PasswordsListViewModel(
    private val passwordService: PasswordService
) : ViewModel(){

    private val _passwords = MutableLiveData<List<Password>>()
    val passwords = _passwords

    private val listener: PasswordListener = {
        _passwords.value = it
    }

    init {
        loadPasswords()
    }

    override fun onCleared() {
        super.onCleared()
        passwordService.removeListener(listener)
    }

    fun loadPasswords() {
        passwordService.addListener(listener)
    }

    fun movePassword(password: Password, moveBy: Int) {
        passwordService.movePassword(password, moveBy)
    }

    fun deletePassword(password: Password) {
        passwordService.deletePassword(password)
    }

    fun addPassword(password: Password) {
        passwordService.addPassword(password)
    }
}