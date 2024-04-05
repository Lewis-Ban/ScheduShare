package com.example.schedushare

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.view.LayoutInflater

class EventsList : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_list_page)

        val goBackEventButton = findViewById<Button>(R.id.goBackFriendListButton)
        val eventCreateButton = findViewById<Button>(R.id.addFriendButton)
        val eventsLayout = findViewById<LinearLayout>(R.id.eventsLayout)

        // set the format for the date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentCal = Calendar.getInstance() // get the current date
        val currentDate = dateFormat.format(currentCal.time)
        currentCal.add(Calendar.DATE, 7) // add seven days to the current date
        val toDate = dateFormat.format(currentCal.time)

        // creating a variable for firebase firestore
        val db = FirebaseFirestore.getInstance()
        db.collection("Events").document(userNm).collection("events").whereGreaterThanOrEqualTo("eventdate", currentDate).whereLessThanOrEqualTo("eventdate",toDate).orderBy("eventdate")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val eventName = document.getString("eventname")
                    val eventDate = document.getString("eventdate")
                    val eventTime = document.getString("eventtime")

                    //Box for event list <-- Need to apply space between boxes, and elevation(shadow on boxes)
                    val eventItemView = LayoutInflater.from(this).inflate(R.layout.event_items_box, null)

                    val eventTextView = eventItemView.findViewById<TextView>(R.id.text_event_item)
                    eventTextView.text = "Event: $eventName\nDate: $eventDate\nTime: $eventTime"
                    eventsLayout.addView(eventItemView)

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