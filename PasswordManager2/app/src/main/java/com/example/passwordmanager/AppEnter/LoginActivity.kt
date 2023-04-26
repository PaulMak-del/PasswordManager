package com.example.passwordmanager.AppEnter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.passwordmanager.MainActivity
import com.example.passwordmanager.R
import com.example.passwordmanager.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        val user = auth.currentUser
        if (user != null) {
            Snackbar.make(view, "User", Snackbar.LENGTH_LONG).show()
            Log.d("ddd", "email: " + user.email.toString())
            Log.d("ddd", "uid: " + user.uid)

        }

        with(binding) {
            registerButton.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()
                if (email.isEmpty() || pass.isEmpty()) {
                    Snackbar.make(view, "Поля не должны быть пустыми", Snackbar.LENGTH_LONG).show()
                } else if (pass.length < 6) {
                    Snackbar.make(view, "Пароль должен содержать минимум 6 символов", Snackbar.LENGTH_LONG).show()
                }
                else {
                    auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                Snackbar.make(view, "Register success", Snackbar.LENGTH_LONG).show()
                            } else {
                                Snackbar.make(view, "Register failed", Snackbar.LENGTH_LONG).show()
                            }
                        }
                }
            }

            buttonConfirm.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()
                if (email.isEmpty() || pass.isEmpty()) {
                    Snackbar.make(view, "Поля не должны быть пустыми", Snackbar.LENGTH_LONG).show()
                } else {
                    auth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                Snackbar.make(view, "SingIn Success", Snackbar.LENGTH_LONG).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Snackbar.make(view, "SingIn Failed", Snackbar.LENGTH_LONG).show()
                            }
                        }
                }
            }
        }
    }
}