package com.example.fetchfood

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.fetchfood.models.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyOders : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()
    private val ordersKey = "orders_products"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oderhistory)

        sharedPreferences = getSharedPreferences("FetchFoodCart", Context.MODE_PRIVATE)

        val orderListView: ListView = findViewById(R.id.orderListView)
        val completeOrderButton: Button = findViewById(R.id.completeOrderButton)

        // Load the checked-out orders from SharedPreferences
        val ordersJson = sharedPreferences.getString(ordersKey, null)
        val orders: MutableList<Product> = if (ordersJson != null) {
            val type = object : TypeToken<MutableList<Product>>() {}.type
            gson.fromJson(ordersJson, type)
        } else {
            mutableListOf()
        }

        // Create a list of strings to display order information
        val orderDetailsList = if (orders.isNotEmpty()) {
            orders.map { product -> "${product.name} - Rs. ${product.price}" }
        } else {
            listOf("No orders found")
        }

        // Set up the ArrayAdapter to display the orders in the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, orderDetailsList)
        orderListView.adapter = adapter

        // Set click listener for the "Back" button
        completeOrderButton.setOnClickListener {
            // Navigate back to the dashboard activity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Close this activity to avoid navigating back here
        }
    }
}
