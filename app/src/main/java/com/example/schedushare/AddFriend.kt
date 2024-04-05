package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
class AddFriend : AppCompatActivity() {
    private lateinit var friendNameEditText: EditText
    private lateinit var friendRelationshipEditText: EditText
    private lateinit var friendUserIDEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addfriend_page)

        friendNameEditText = findViewById(R.id.name)
        friendRelationshipEditText = findViewById(R.id.Relationship)
        friendUserIDEditText = findViewById(R.id.Description)

        val goBackButton = findViewById<Button>(R.id.goBackFriendListButton)
        val addFriendButton = findViewById<Button>(R.id.friendAddButton)

        val db = FirebaseFirestore.getInstance()

        goBackButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
            finish()
        }

        addFriendButton.setOnClickListener {
            val friendName = friendNameEditText.text.toString()
            val friendRelationship = friendRelationshipEditText.text.toString()
            val friendUserID = friendUserIDEditText.text.toString()

            val newFriend = hashMapOf(
                "name" to friendName,
                "relationship" to friendRelationship,
                "userID" to friendUserID
            )

            db.collection("Friends").document("example").collection("examplefriend")
                .add(newFriend)
                .addOnSuccessListener {
                    val intent = Intent(this, FriendsList::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to add new address", Toast.LENGTH_SHORT).show()
                }
        }
    }
}