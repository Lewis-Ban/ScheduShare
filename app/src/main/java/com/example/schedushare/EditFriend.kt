package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast
class EditFriend : AppCompatActivity() {
    private lateinit var friendNameEditText: EditText
    private lateinit var friendRelationshipEditText: EditText
    private lateinit var friendUserIDEditText: EditText
    private lateinit var friendId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editfriend_page)

        friendNameEditText = findViewById(R.id.name)
        friendRelationshipEditText = findViewById(R.id.Relationship)
        friendUserIDEditText = findViewById(R.id.Description)

        // Initialize click listener
        val goBackButton = findViewById<Button>(R.id.goBackFriendListButton)
        val addFriendButton = findViewById<Button>(R.id.friendAddButton)
        val deleteFriendButton = findViewById<Button>(R.id.Delete)
        val goToCalendarButton = findViewById<Button>(R.id.Calendar)

        friendId = intent.getStringExtra("friendId") ?: ""

        val db = FirebaseFirestore.getInstance()

        db.collection("Friends").document(userNm).collection(userNm+"friend").document(friendId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val friendName = document.getString("name")
                    val friendRelationship = document.getString("relationship")
                    val friendUserID = document.getString("userID")
                    idClick = friendUserID.toString()
                    nameClick = friendName.toString()
                    friendNameEditText.setText(friendName)
                    friendRelationshipEditText.setText(friendRelationship)
                    friendUserIDEditText.setText(friendUserID)
                } else {
                    Toast.makeText(this, "Failed to find document", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error getting documents: $exception", Toast.LENGTH_LONG).show()
            }


        // Click listener

        addFriendButton.setOnClickListener {
            val updatedName = friendNameEditText.text.toString()
            val updatedRelationship = friendRelationshipEditText.text.toString()
            val updatedUserID = friendUserIDEditText.text.toString()

            if (updatedName.isNotEmpty() || updatedRelationship.isNotEmpty() || updatedUserID.isNotEmpty()) {
                val updatedFriend = hashMapOf<String, Any>()

                if (updatedName.isNotEmpty()) {
                    updatedFriend["name"] = updatedName
                }

                if (updatedRelationship.isNotEmpty()) {
                    updatedFriend["relationship"] = updatedRelationship
                }

                if (updatedUserID.isNotEmpty()) {
                    updatedFriend["userID"] = updatedUserID
                }

                db.collection("Friends").document("example").collection("examplefriend").document(friendId)
                    .update(updatedFriend)
                    .addOnSuccessListener {
                        finish()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "Error updating friend: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "There are no changes to update", Toast.LENGTH_SHORT).show()
            }
        }

        deleteFriendButton.setOnClickListener {
            db.collection("Friends").document("example").collection("examplefriend").document(friendId)
                .delete()
                .addOnSuccessListener {
                    // Friend deleted successfully
                    val intent = Intent(this, FriendsList::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Unexpected error occurred", Toast.LENGTH_SHORT).show()
                }
        }

        goBackButton.setOnClickListener {
            val intent = Intent(this, FriendsList::class.java)
            startActivity(intent)
        }

        goToCalendarButton.setOnClickListener {
            val intent = Intent(this, FriendCalendar::class.java)
            startActivity(intent)
        }
    }
}