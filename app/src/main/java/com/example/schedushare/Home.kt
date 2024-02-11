package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

        summaryButton.setOnClickListener {
            val intent = Intent(this, SummaryActivity::class.java)
            startActivity(intent)
        }

        friendsButton.setOnClickListener {
            val intent = Intent(this, FriendsActivity::class.java)
            startActivity(intent)
        }
    }
}


