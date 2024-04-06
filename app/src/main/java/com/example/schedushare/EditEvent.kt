package com.example.schedushare

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class EditEvent : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editevent_page)

        val goBackEventButton = findViewById<Button>(R.id.goBackFriendListButton)
        val eventSaveButton = findViewById<Button>(R.id.friendAddButton)
        val deleteButton = findViewById<Button>(R.id.delete_button)

        val ename = findViewById<EditText>(R.id.editName)
        val edate = findViewById<EditText>(R.id.editDate)
        val etime = findViewById<EditText>(R.id.editTime)
        val ememo = findViewById<EditText>(R.id.editMemo)
        // on below line we are adding
        // click listener for our edit text.
        edate.setOnClickListener {
            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()
            // on below line we are getting
            // our day, month and year.
            val curyear = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (String.format("%02d", year) + "-" + String.format("%02d", monthOfYear) + "-" + String.format("%02d", dayOfMonth))
                    edate.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                curyear,
                month,
                day
            )
            datePickerDialog.show()
        }

        etime.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker = TimePickerDialog(this, { timePicker, selectedHour, selectedMinute ->
                val tim = (String.format("%02d", selectedHour)+":"+String.format("%02d", selectedMinute))
                etime.setText(tim)
            },
                hour,
                minute,
                true)
            mTimePicker.show()
        }

        //Get passed event id
        val eventId = intent.getStringExtra("eventId")

        val db = FirebaseFirestore.getInstance()

        if (eventId != null) {
            db.collection("Events").document(userNm).collection("events").document(eventId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val eventName = document.getString("eventname")
                        val eventDate = document.getString("eventdate")
                        val eventTime = document.getString("eventtime")
                        val eventMemo = document.getString("eventmemo")

                        ename.setText(eventName)
                        edate.setText(eventDate)
                        etime.setText(eventTime)
                        ememo.setText(eventMemo)
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        }

//        data class Newevent(
//            val eventname: String? = null,
//            val eventdate: String? = null,
//            val eventtime: String? = null,
//            val eventmemo: String? = null,
//        )

        goBackEventButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
            finish()
        }

        eventSaveButton.setOnClickListener {
            val updatedName = ename.text.toString()
            val updatedDate = edate.text.toString()
            val updatedTime = etime.text.toString()
            val updatedMemo = ememo.text.toString()

            if (updatedName.isNotEmpty() || updatedDate.isNotEmpty() || updatedTime.isNotEmpty() || updatedMemo.isNotEmpty()) {
                val updatedEvent = hashMapOf<String, Any>()

                if (updatedName.isNotEmpty()) {
                    updatedEvent["eventname"] = updatedName
                }

                if (updatedDate.isNotEmpty()) {
                    updatedEvent["eventdate"] = updatedDate
                }

                if (updatedTime.isNotEmpty()) {
                    updatedEvent["eventtime"] = updatedTime
                }

                if (updatedMemo.isNotEmpty()) {
                    updatedEvent["eventmemo"] = updatedMemo
                }

                if (eventId != null) {
                    db.collection("Events").document(userNm).collection("events").document(eventId)
                        .update(updatedEvent)
                        .addOnSuccessListener {
                            finish()
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(this, "Error updating event: ${exception.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            } else {
                Toast.makeText(this, "There are no changes to update", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            if (eventId != null) {
                db.collection("Events").document(userNm).collection("events").document(eventId)
                    .delete()
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
            }

            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
            finish()
        }
    }
}
