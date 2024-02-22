package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
class EditEvent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editevent_page)

        val goBackEventButton = findViewById<Button>(R.id.goBackEventButton)
        val eventSaveButton = findViewById<Button>(R.id.eventSaveButton)
        val deleteButton = findViewById<Button>(R.id.delete_button)


        goBackEventButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
            finish()
        }

        eventSaveButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
        }
    }
}