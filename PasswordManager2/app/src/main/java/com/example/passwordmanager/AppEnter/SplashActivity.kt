package com.example.passwordmanager.appEnter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ddd", "SplashActivity")
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}