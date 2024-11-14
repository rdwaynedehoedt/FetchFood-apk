package com.example.fetchfood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.example.fetchfood.models.Product


class DashboardActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Initialize views
        val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        val viewRecommendation = findViewById<RecyclerView>(R.id.viewRecommendation)
        val exploreButton = findViewById<LinearLayout>(R.id.exploreButton)
        val cartButton = findViewById<LinearLayout>(R.id.cartButton)
        val userButton = findViewById<LinearLayout>(R.id.userButton1)
        val orderButton = findViewById<LinearLayout>(R.id.MyOder)
        val newsButton = findViewById<LinearLayout>(R.id.news) // Renamed for clarity

        // Set up the RecyclerView
        viewRecommendation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Fetch data from Firestore
        fetchProducts(viewRecommendation)

        // Set click listeners
        exploreButton.setOnClickListener {
            startActivity(Intent(this, ExploreActivity::class.java))
        }

        cartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        userButton.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        orderButton.setOnClickListener {
            startActivity(Intent(this, MyOders::class.java))
        }

        newsButton.setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }
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
                recyclerView.adapter = ProductAdapter(productList)
            }
            .addOnFailureListener { exception ->
                Log.w("DashboardActivity", "Error getting documents: ", exception)
            }
    }
}
