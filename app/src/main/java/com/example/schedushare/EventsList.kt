package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class EventsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_list_page)

        // Initialize click listener
        val goBackEventButton = findViewById<Button>(R.id.goBackEventButton)
        val eventCreateButton = findViewById<Button>(R.id.eventCreateButton)

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
    }
}