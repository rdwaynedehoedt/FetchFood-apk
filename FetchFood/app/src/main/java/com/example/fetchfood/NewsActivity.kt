package com.example.fetchfood

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news) // Set the content view to the correct XML layout

        // Find the back button by its ID
        val backButton: ImageView = findViewById(R.id.backButton)

        // Handle the back button click event
        backButton.setOnClickListener {
            onBackPressed() // Navigate back to the previous activity
        }
    }
}
