package com.example.schedushare

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

var userNm:String = ""
var userPss:String = ""

class Login : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val username = findViewById<EditText>(R.id.idEditText)
        val userpass = findViewById<EditText>(R.id.passwordEditText)

        loginButton.setOnClickListener {
            userNm = username.text.toString()
            userPss = userpass.text.toString()
            val db = Firebase.firestore
            db.collection("Users").whereEqualTo("userID", userNm).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        if(document.getString("Password") == userPss) {
                            val mySnackbar = Snackbar.make(findViewById<Button>(R.id.loginButton), "Login Successful", Snackbar.LENGTH_LONG)
                            mySnackbar.show()
                            val intent = Intent(this, Home::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            val mySnackbar = Snackbar.make(findViewById<Button>(R.id.loginButton), "Wrong Password ", Snackbar.LENGTH_SHORT)
                            mySnackbar.show()
                        }
                    }
                }

        }


    }
//    fun authenticateUser(username: String, password: String): Boolean {
//        //Log in process
//        var validP = false
//        val db = Firebase.firestore
//        db.collection("Users").whereEqualTo("userID", username).get()
//            .addOnSuccessListener { documents ->
//                for (document in documents) {
//                    if(document.getString("Password") == password) {
//                        validP = true
//                        val mySnackbar = Snackbar.make(findViewById<Button>(R.id.loginButton), "$validP", Snackbar.LENGTH_SHORT)
//                        mySnackbar.show()
//                    }
//                }
//            }
//        return validP
//    }


//    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
//    auth = Firebase.auth
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser: FirebaseUser = mAuth.getCurrentUser()
//        if (currentUser != null) {
//            reload()
//        }
//    }
//    auth.createUserWithEmailAndPassword(email, password)
//    .addOnCompleteListener(this) { task ->
//        if (task.isSuccessful) {
//            // Sign in success, update UI with the signed-in user's information
//            Log.d(TAG, "createUserWithEmail:success")
//            val user = auth.currentUser
//            updateUI(user)
//        } else {
//            // If sign in fails, display a message to the user.
//            Log.w(TAG, "createUserWithEmail:failure", task.exception)
//            Toast.makeText(
//                baseContext,
//                "Authentication failed.",
//                Toast.LENGTH_SHORT,
//            ).show()
//            updateUI(null)
//        }
//    }
//    auth.signInWithEmailAndPassword(email, password)
//    .addOnCompleteListener(this) { task ->
//        if (task.isSuccessful) {
//            // Sign in success, update UI with the signed-in user's information
//            Log.d(TAG, "signInWithEmail:success")
//            val user = auth.currentUser
//            updateUI(user)
//        } else {
//            // If sign in fails, display a message to the user.
//            Log.w(TAG, "signInWithEmail:failure", task.exception)
//            Toast.makeText(
//                baseContext,
//                "Authentication failed.",
//                Toast.LENGTH_SHORT,
//            ).show()
//            updateUI(null)
//        }
//    }

}


