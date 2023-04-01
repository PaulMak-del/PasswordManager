package com.example.passwordmanager.AppEnter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.passwordmanager.MainActivity
import com.example.passwordmanager.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.buttonConfirm.setOnClickListener {
            // TODO("Add checking password in DB")
            if (true) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Password Incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }
}