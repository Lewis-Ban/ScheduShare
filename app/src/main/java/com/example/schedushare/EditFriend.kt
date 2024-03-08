package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
class EditFriend : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editfriend_page)

        // Initialize click listener
        val goBackButton = findViewById<Button>(R.id.goBackFriendListButton)
        val addFriendButton = findViewById<Button>(R.id.friendAddButton)
        val deleteFriendButton = findViewById<Button>(R.id.Delete)
        val goToCalendarButton = findViewById<Button>(R.id.Calendar)


        // Click listener
        goBackButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
            finish()
        }

        addFriendButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }

        deleteFriendButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }

        goToCalendarButton.setOnClickListener {
            val intent = Intent(this, FriendCalendar::class.java)
            startActivity(intent)
        }
    }
}