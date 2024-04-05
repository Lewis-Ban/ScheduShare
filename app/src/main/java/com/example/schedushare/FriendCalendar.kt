package com.example.schedushare

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


var idClick = ""
var nameClick = ""
class FriendCalendar : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friend_calendar_page)

        val goBackButton = findViewById<Button>(R.id.friend_calendar_button)
        val friendlist = findViewById<TextView>(R.id.textView6)
        val eventsLayout = findViewById<LinearLayout>(R.id.eventsLayout)
        friendlist.text = "$nameClick's Schedule"
        // set the format for the date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentCal = Calendar.getInstance() // get the current date
        val currentDate = dateFormat.format(currentCal.time)
        currentCal.add(Calendar.DATE, 7) // add seven days to the current date
        val toDate = dateFormat.format(currentCal.time)

        // creating a variable for firebase firestore
        val db = FirebaseFirestore.getInstance()
        db.collection("Events").document(idClick).collection("events").whereGreaterThanOrEqualTo("eventdate", currentDate).whereLessThanOrEqualTo("eventdate",toDate).orderBy("eventdate")
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
        // Click listener
        goBackButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
            finish()
        }
    }
}