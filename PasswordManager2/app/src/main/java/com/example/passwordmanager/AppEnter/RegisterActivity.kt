package com.example.passwordmanager.AppEnter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.passwordmanager.MainActivity
import com.example.passwordmanager.R
import com.example.passwordmanager.UserData.UserPassword
import com.example.passwordmanager.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        binding.buttonConfirm.setOnClickListener {
            if (binding.editTextTextPassword.text == binding.editTextTextPasswordConfirm.text) {
                // val password = UserPassword(binding.editTextTextPasswordConfirm.text.toString())
                // TODO("Safe password to database or smt, idk")

                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.password_not_equal_alert, Toast.LENGTH_SHORT).show()
            }
        }
    }
}