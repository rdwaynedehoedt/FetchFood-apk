package com.example.fetchfood

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchfood.models.Product

class CartActivity : AppCompatActivity() {

    private val cartProductList = ArrayList<Product>()

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

        // Setup RecyclerView
        viewCart.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(cartProductList)
        viewCart.adapter = adapter

        // Add product from intent to cart
        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("productDescription")
        val productPrice = intent.getIntExtra("productPrice", 0)
        val productImageURL = intent.getStringExtra("productImage")

        if (productName != null && productDescription != null && productImageURL != null) {
            val product = Product(productName, productDescription, productPrice, productImageURL)
            cartProductList.add(product)
            adapter.notifyDataSetChanged()
        }
    }
}
