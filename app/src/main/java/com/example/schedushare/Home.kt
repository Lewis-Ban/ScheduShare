package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        // Initialize click listener
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val eventsButton = findViewById<Button>(R.id.eventsButton)
        val summaryButton = findViewById<Button>(R.id.summaryButton)
        val friendsButton = findViewById<Button>(R.id.friendsButton)

        // Click listener
        logoutButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        eventsButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
        }

        summaryButton.setOnClickListener {
            val intent = Intent(this, Summary::class.java)
            startActivity(intent)
        }

        friendsButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }
    }
}


