package com.example.passwordmanager.AppEnter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var intent: Intent

        if (true) {
            // If user have a password
            intent = Intent(this@SplashActivity, LoginActivity::class.java)
        } else {
            // On user first start
            intent = Intent(this@SplashActivity, RegisterActivity::class.java)
        }
        startActivity(intent)
        Log.d("ddd", "SplashActivity")
    }
}