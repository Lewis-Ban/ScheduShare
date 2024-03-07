package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class EventsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_list_page)

        // Initialize click listener
        val goBackEventButton = findViewById<Button>(R.id.goBackFriendListButton)
        val eventCreateButton = findViewById<Button>(R.id.addFriendButton)
        val scheduleLink1 = findViewById<TextView>(R.id.schedule1)
        val scheduleLink2 = findViewById<TextView>(R.id.schedule2)
        val scheduleLink3 = findViewById<TextView>(R.id.schedule3)

        // Click listener
        goBackEventButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        eventCreateButton.setOnClickListener {
            val intent = Intent(this, AddEvent::class.java)
            startActivity(intent)
        }

        scheduleLink1.setOnClickListener {
            val intent = Intent(this, EditEvent::class.java)
            startActivity(intent)
        }

        scheduleLink2.setOnClickListener {
            val intent = Intent(this, EditEvent::class.java)
            startActivity(intent)
        }

        scheduleLink3.setOnClickListener {
            val intent = Intent(this, EditEvent::class.java)
            startActivity(intent)
        }

    }
}