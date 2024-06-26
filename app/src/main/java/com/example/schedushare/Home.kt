package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        // Initialize click listener
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val eventsButton = findViewById<Button>(R.id.eventsButton)
        val summaryButton = findViewById<Button>(R.id.summaryButton)
        val friendsButton = findViewById<Button>(R.id.friendsButton)
        val comingEvent = findViewById<TextView>(R.id.comingEventTextView)
        val comingTime = findViewById<TextView>(R.id.timeSavedTextView)

        // set the format for the date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentCal = Calendar.getInstance() // get the curretn date
        val currentDate = dateFormat.format(currentCal.time)

        // creating a variable for firebase firestore
        val db = Firebase.firestore
        var taskname: String
        var t: String
        db.collection("Events").document(userNm).collection("events").whereGreaterThanOrEqualTo("eventdate", currentDate).orderBy("eventdate").limit(1).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    taskname = document.getString("eventname").toString()
                    t = (document.getString("eventdate").toString() +"\n       "+ document.getString("eventtime").toString())
                    comingEvent.text = taskname
                    comingTime.text = t
                }
            }

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

        friendsButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }

    }
}


