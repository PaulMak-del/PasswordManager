package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.passwordmanager.UserData.UserPassword
import com.example.passwordmanager.databinding.ActivityChangePasswordBinding
import com.google.android.material.snackbar.Snackbar

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private val userPassword = UserPassword("pass")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonConfirm.setOnClickListener {
            Log.d("ddd", "ChangePasswordActivity: button clicked")
            // TODO("Take user password from DB")
            if (binding.editTextOldPassword.text.toString() == userPassword.text) {
                if (binding.editTextNewPassword.text.toString() == binding.editTextNewPasswordConfirm.text.toString()) {
                    // TODO("Change user password in DB")
                    userPassword.text = binding.editTextNewPassword.text.toString()
                    Snackbar.make(binding.root, R.string.password_change_toast, Snackbar.LENGTH_LONG).show()
                    val intent = Intent(this@ChangePasswordActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, R.string.password_not_equal_alert, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, R.string.incorrect_password, Toast.LENGTH_LONG).show()
            }
        }
    }
}