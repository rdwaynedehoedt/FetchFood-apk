package com.example.fetchfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Make sure to reference the correct layout file here

        // Initialize views
        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        val viewCategory = findViewById<RecyclerView>(R.id.viewCategory)
        val viewRecommendation = findViewById<RecyclerView>(R.id.viewRecommendation)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // TODO: Add logic to handle data and interactions for RecyclerViews and BottomNavigationView
    }
}
