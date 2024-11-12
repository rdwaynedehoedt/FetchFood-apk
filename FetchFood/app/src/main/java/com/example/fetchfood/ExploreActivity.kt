package com.example.fetchfood

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchfood.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class ExploreActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

        // Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Initialize views
        val viewList = findViewById<RecyclerView>(R.id.viewList)
        val backBtn = findViewById<ImageView>(R.id.backBtn)

        // Setup RecyclerView with GridLayoutManager for 2 columns
        viewList.layoutManager = GridLayoutManager(this, 2)

        // Fetch and display data from Firestore
        fetchProducts(viewList)

        // Set up back button click listener
        backBtn.setOnClickListener {
            // Navigate back to DashboardActivity
            finish() // Simply finish this activity to go back to the previous one
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
                // Assuming you have a ProductAdapter to display products
                val adapter = ProductAdapter(productList)
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("ExploreActivity", "Error getting documents: ", exception)
            }
    }
}
