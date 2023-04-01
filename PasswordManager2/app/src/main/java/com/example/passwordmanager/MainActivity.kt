package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("ddd", "MainActivity")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_change_password-> {
                Toast.makeText(this, "change", Toast.LENGTH_LONG).show()
            }
            R.id.action_favorite-> Toast.makeText(this, "favorite", Toast.LENGTH_LONG).show()
            R.id.action_all_passwords-> Toast.makeText(this, "all", Toast.LENGTH_LONG).show()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}