package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class EventsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_list_page)

        val goBackEventButton = findViewById<Button>(R.id.goBackFriendListButton)
        val eventCreateButton = findViewById<Button>(R.id.addFriendButton)
        val eventsLayout = findViewById<LinearLayout>(R.id.eventsLayout)

        val db = FirebaseFirestore.getInstance()

        db.collection("Events")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val eventName = document.getString("eventname")
                    val eventDate = document.getString("eventdate")
                    val eventTime = document.getString("eventtime")

                    val eventTextView = TextView(this)
                    eventTextView.text = "Event: $eventName\nDate: $eventDate\nTime: $eventTime"
                    eventsLayout.addView(eventTextView)

                    eventTextView.setOnClickListener {
                        val intent = Intent(this, EditEvent::class.java)
                        startActivity(intent)
                    }
                }
            }

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