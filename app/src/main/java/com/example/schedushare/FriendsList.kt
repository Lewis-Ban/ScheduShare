package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
class FriendsList : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_list_page)

        // Initialize click listener
        val goBackButton = findViewById<Button>(R.id.goBackFriendListButton)
        val addFriendButton = findViewById<Button>(R.id.addFriendButton)
        val friendListView = findViewById<ListView>(R.id.friendList)
        val searchView = findViewById<SearchView>(R.id.searchView)

        val db = FirebaseFirestore.getInstance()

        db.collection("Friends").document(userNm).collection(userNm+"friend")
            .get()
            .addOnSuccessListener { result ->
                val friendList = ArrayList<String>()
                val friendIdList = ArrayList<String>()
                for (document in result) {
                    val friendName = document.getString("name")
                    val friendId = document.id
                    if (friendName != null) {
                        friendList.add(friendName)
                        friendIdList.add(friendId)
                    }
                }

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friendList)
                friendListView.adapter = adapter

                for (i in friendList.indices) {
                    friendListView.getChildAt(i)?.tag = friendIdList[i]
                }
                friendListView.setOnItemClickListener { _, _, position, _ ->
                    val selectedFriendId = friendIdList[position]
                    val intent = Intent(this@FriendsList, EditFriend::class.java)
                    intent.putExtra("friendId", selectedFriendId)
                    startActivity(intent)
                }
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

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapter.filter.filter(it) }
                return false
            }
        })
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                searchView.requestFocus()
            }
        }
    }
}