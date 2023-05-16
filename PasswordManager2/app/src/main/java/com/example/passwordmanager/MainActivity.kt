package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.passwordmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ddd", "MainActivity")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d("ddd", "MainActivity: onCreateOptionMenu")
        menuInflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("ddd", "MainActivity: onOptionItemSelected")
        when (item.itemId) {
            R.id.action_change_password-> {
                Toast.makeText(this, "change", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, ChangePasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.action_favorite-> {
                Toast.makeText(this, "favorite", Toast.LENGTH_LONG).show()
                // Должен сказать адаптеру поменять список паролей и уведомить его об изменених
            }
            R.id.action_all_passwords-> {
                Toast.makeText(this, "all", Toast.LENGTH_LONG).show()
                // Должен сказать адаптеру поменять список паролей и уведомить его об изменених
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}