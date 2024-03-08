package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
class FriendsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_list_page)

        // Initialize click listener
        val goBackButton = findViewById<Button>(R.id.goBackFriendListButton)
        val addFriendButton = findViewById<Button>(R.id.addFriendButton)
        val friendListView = findViewById<ListView>(R.id.friendList)

        //ListView
        val friendArray = resources.getStringArray(R.array.sections)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friendArray)
        friendListView.adapter = adapter

        friendListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItem = friendArray[position]
            val intent = Intent(this, EditFriend::class.java)
            intent.putExtra("friendName", selectedItem)
            startActivity(intent)
        }

        // Click listener
        goBackButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        addFriendButton.setOnClickListener {
            val intent = Intent(this, AddFriend::class.java)
            startActivity(intent)
        }


    }
}