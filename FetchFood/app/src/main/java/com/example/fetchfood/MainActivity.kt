package com.example.fetchfood

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Check if the user is logged in
        if (auth.currentUser != null) {
            // If user is already logged in, go directly to DashboardActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Finish MainActivity to prevent going back to it
        } else {
            // User is not logged in, show welcome screen
            setContentView(R.layout.activity_intro)

            // Initialize views
            val getStartedButton: TextView = findViewById(R.id.button)

            // Set OnClickListener for the "Get Started" button
            getStartedButton.setOnClickListener {
                // Navigate to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
