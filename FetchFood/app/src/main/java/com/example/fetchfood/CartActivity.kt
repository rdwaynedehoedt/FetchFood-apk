package com.example.fetchfood

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart) // Linking XML layout to this Activity

        // Initialize views
        val backBtn = findViewById<ImageView>(R.id.backBtn)
        val viewCart = findViewById<RecyclerView>(R.id.viewCart)

        // Set click listener for back button
        backBtn.setOnClickListener {
            // Finish this activity and go back to the previous screen
            finish()
        }

        // Other functionality related to your cart can be added here
    }
}
