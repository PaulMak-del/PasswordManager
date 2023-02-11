package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(4000)
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        Log.d("ddd", "SplashActivity")
    }
}