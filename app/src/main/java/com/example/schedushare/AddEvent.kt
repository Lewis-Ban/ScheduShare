package com.example.schedushare

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import java.util.Calendar

class AddEvent : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addevent_page)

        val goBackEventButton = findViewById<Button>(R.id.goBackFriendListButton)
        val eventCreateButton = findViewById<Button>(R.id.addFriendButton)

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
                    val dat = (year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())
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
        var eventcount = 1
        val db = Firebase.firestore
        val query = db.collection("Events").document("example").collection("events")
        query.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    eventcount += 1
                }
            }


        data class Newevent(
            val eventname: String? = null,
            val eventdate: String? = null,
            val eventtime: String? = null,
            val eventmemo: String? = null,
        )


        goBackEventButton.setOnClickListener {
            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
            finish()
        }

        eventCreateButton.setOnClickListener {
            val newe = Newevent(
                ename.text.toString(),
                edate.text.toString(),
                etime.text.toString(),
                ememo.text.toString(),
            )

            db.collection("Events").document("example").collection("events").document("event$eventcount").set(newe, SetOptions.merge())


            val intent = Intent(this, EventsList::class.java)
            startActivity(intent)
        }
    }
}