package com.example.passwordmanager.model

import android.util.Log
import java.util.*

typealias PasswordListener = (passwords: List<Password>) -> Unit

class PasswordService {

    private var passwords: MutableList<Password> = mutableListOf<Password>()
    private val listeners = mutableSetOf<PasswordListener>()

    init {
        passwords.add(Password(12, "name1", "password1"))
        passwords.add(Password(13, "name2", "password2"))
        passwords.add(Password(14, "name3", "password3"))
        passwords.add(Password(15, "name4", "password4"))
    }

    fun getPasswords(): List<Password> {
        return passwords;
    }

    fun addPassword(password: Password) {
        passwords.add(password)
    }

    fun deletePassword(password: Password) {
        val indexToDelete: Int = passwords.indexOfFirst {it.id == password.id }
        if (indexToDelete != -1 ) {
            passwords.removeAt(indexToDelete)
            notifyChanges()
        }
        else {
            Log.d("ddd", "deletePassword: Index not found")
        }
    }

    fun movePassword(password: Password, moveBy: Int) {
        val oldIndex: Int = passwords.indexOfFirst { it.id == password.id }
        if (oldIndex == -1) {
            Log.d("ddd", "movePassword: oldIndex not found")
            return
        }
        val newIndex: Int = oldIndex + moveBy
        if (newIndex < 0 || newIndex > passwords.size) {
            Log.d("ddd", "movePassword: newIndex out of borders")
            return
        }
        Collections.swap(passwords, oldIndex, newIndex)
        notifyChanges()
    }

    fun addListener(listener: PasswordListener) {
        listeners.add(listener)
        listener.invoke(passwords)
    }

    fun removeListener(listener: PasswordListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach{ it.invoke(passwords) }
    }

}











