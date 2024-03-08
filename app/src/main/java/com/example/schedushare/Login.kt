package com.example.schedushare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun authenticateUser(username: String, password: String): Boolean {
        //Log in process
        return username.isNotEmpty() && password.isNotEmpty()
    }


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


