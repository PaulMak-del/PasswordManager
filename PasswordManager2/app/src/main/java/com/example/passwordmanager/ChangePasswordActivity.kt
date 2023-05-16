package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.passwordmanager.databinding.ActivityChangePasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        user = FirebaseAuth.getInstance().currentUser!!

        with(binding) {
            buttonConfirm.setOnClickListener {
                Log.d("ddd", "ChangePasswordActivity: button clicked")

                val oldPass = editTextOldPassword.text.toString()
                val newPass = editTextNewPassword.text.toString()
                val newPassConf = editTextNewPasswordConfirm.text.toString()

                if (oldPass.trim().isEmpty() || newPass.trim().isEmpty() || newPassConf.trim().isEmpty()) {
                    Snackbar.make(view, "Поля не должны быть пустыми", Snackbar.LENGTH_LONG).show()
                } else {
                    if (newPass.trim() != newPassConf.trim()) {
                        Snackbar.make(view, "Пароли не совпадают", Snackbar.LENGTH_LONG).show()
                    } else {
                        user.updatePassword(newPass.trim())
                            .addOnCompleteListener {task ->
                                if (task.isSuccessful) {
                                    Log.d("ddd", "Change password success")
                                    val intent = Intent(this@ChangePasswordActivity, MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Log.d("ddd", "Change password failed")
                                    Snackbar.make(view, "Неправильный пароль", Snackbar.LENGTH_LONG).show()
                                }
                            }
                    }
                }
            }
        }
    }
}