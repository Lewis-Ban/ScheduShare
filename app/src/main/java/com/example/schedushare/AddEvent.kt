package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
class AddEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addevent_page)

        val goBackEventButton = findViewById<Button>(R.id.goBackFriendListButton)
        val eventCreateButton = findViewById<Button>(R.id.addFriendButton)

        goBackEventButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
            finish()
        }

        eventCreateButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
        }
    }
}