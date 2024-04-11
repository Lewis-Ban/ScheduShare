package com.example.schedushare

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EventsList : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events_list_page)

        val goBackEventButton = findViewById<Button>(R.id.goBackFriendListButton)
        val eventCreateButton = findViewById<Button>(R.id.addFriendButton)
        val eventsListView = findViewById<ListView>(R.id.eventsListView)

        // set the format for the date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentCal = Calendar.getInstance() // get the current date
        val currentDate = dateFormat.format(currentCal.time)
        currentCal.add(Calendar.DATE, 7) // add seven days to the current date
        val toDate = dateFormat.format(currentCal.time)

        // creating a variable for firebase firestore
        val db = FirebaseFirestore.getInstance()

        db.collection("Events").document(userNm).collection("events")
            .whereGreaterThanOrEqualTo("eventdate", currentDate).orderBy("eventdate")
            .get()
            .addOnSuccessListener { result ->
                val eventList = ArrayList<String>()
                val eventIdList = ArrayList<String>()
                for (document in result) {
                    //Log.d("Firestore", "Document snapshot: $document")
                    val eventName = document.getString("eventname")
                    val eventDate = document.getString("eventdate")
                    val eventTime = document.getString("eventtime")
                    val eventId = document.id

                    if (eventName != null && eventDate != null && eventTime != null) {
                        val eventDateTime = "$eventName - Date: $eventDate Time: $eventTime"
                        eventList.add(eventDateTime)
                        eventIdList.add(eventId)
                    }
                }
                adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, eventList)
                eventsListView.adapter = adapter

                for (i in eventList.indices) {
                    eventsListView.getChildAt(i)?.tag = eventIdList[i]
                }
                eventsListView.setOnItemClickListener { _, _, position, _ ->
                    val selectedEventId = eventIdList[position]
                    val intent = Intent(this@EventsList, EditEvent::class.java)
                    intent.putExtra("eventId", selectedEventId)
                    startActivity(intent)
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