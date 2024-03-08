package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
class AddFriend : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addfriend_page)

        // Initialize click listener
        val goBackButton = findViewById<Button>(R.id.goBackFriendListButton)
        val AddFriendButton = findViewById<Button>(R.id.friendAddButton)


        // Click listener
        goBackButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
            finish()
        }

        AddFriendButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }


    }
}