package com.example.fetchfood

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.example.fetchfood.models.Product // Assuming you have a Product model class

class DashboardActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Initialize views
        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        val viewCategory = findViewById<RecyclerView>(R.id.viewCategory)
        val viewRecommendation = findViewById<RecyclerView>(R.id.viewRecommendation)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Setup RecyclerView (e.g. for Category Products)
        viewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewRecommendation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // Set up layout for Recommendations

        // Fetch data from Firestore for Recommendations
        fetchProducts(viewRecommendation)
    }

    private fun fetchProducts(recyclerView: RecyclerView) {
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val productList = ArrayList<Product>()
                for (document in result) {
                    val product = document.toObject(Product::class.java)
                    productList.add(product)
                }
                // Assuming you have a ProductAdapter to display products
                val adapter = ProductAdapter(productList)
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("DashboardActivity", "Error getting documents: ", exception)
            }
    }
}
