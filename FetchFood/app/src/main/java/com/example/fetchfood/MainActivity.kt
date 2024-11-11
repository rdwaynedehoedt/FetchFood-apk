package com.example.fetchfood


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)  // Load activity_intro.xml layout

        // Initialize views
        val imageView2: ImageView = findViewById(R.id.imageView2)
        val imageView3: ImageView = findViewById(R.id.imageView3)
        val textView: TextView = findViewById(R.id.textView)
        val textView2: TextView = findViewById(R.id.textView2)
        val getStartedButton: TextView = findViewById(R.id.button)

        // Set OnClickListener for the "Get Started" button
        getStartedButton.setOnClickListener {
            // Navigate to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

