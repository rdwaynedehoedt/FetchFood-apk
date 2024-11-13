package com.example.fetchfood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user) // Ensure this matches your layout file name

        // Initialize Firebase Auth and Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Initialize views
        val userNameTextView: TextView = findViewById(R.id.userName)
        val userEmailTextView: TextView = findViewById(R.id.userEmail)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        // Get current user
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Set user email
            userEmailTextView.text = currentUser.email

            // Fetch user name from Firestore (assuming user data is stored in a "users" collection)
            val userId = currentUser.uid
            db.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val userName = document.getString("name")
                        userNameTextView.text = userName ?: "Unknown User"
                    }
                }
                .addOnFailureListener {
                    userNameTextView.text = "Failed to load user name"
                }
        }

        // Set logout button click listener
        logoutButton.setOnClickListener {
            auth.signOut()
            // Redirect to login page after logout
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
