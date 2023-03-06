package com.example.passwordmanager

import android.app.Application
import com.example.passwordmanager.model.PasswordService

class App : Application() {

    val passwordService = PasswordService()
}