package com.example.fetchfood

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.fetchfood.models.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartActivity : AppCompatActivity() {

    private val cartProductList = mutableListOf<Product>()
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()
    private val cartKey = "cart_products"
    private val ordersKey = "orders_products"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        sharedPreferences = getSharedPreferences("FetchFoodCart", Context.MODE_PRIVATE)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        val viewCart = findViewById<RecyclerView>(R.id.viewCart)
        val subtotalTxt = findViewById<TextView>(R.id.totalFeeTxt)
        val deliveryTxt = findViewById<TextView>(R.id.deliverytxt)
        val taxTxt = findViewById<TextView>(R.id.taxTxt)
        val totalTxt = findViewById<TextView>(R.id.totalTxt)
        val removeAllButton = findViewById<Button>(R.id.removeAllButton)
        val checkoutButton = findViewById<Button>(R.id.button2) // Reference to the Checkout button

        // Load cart data from SharedPreferences
        loadCartData()

        // Get the product passed from the ProductDetailsActivity
        val productName = intent.getStringExtra("productName")
        val productPrice = intent.getIntExtra("productPrice", 0)
        val productImage = intent.getStringExtra("productImage")
        val productDescription = intent.getStringExtra("productDescription")

        // Add product to cart list if data is available
        if (productName != null && productImage != null && productDescription != null) {
            val product = Product(
                name = productName,
                price = productPrice,
                imageURL = productImage,
                description = productDescription
            )
            cartProductList.add(product)
            saveCartData() // Save the updated cart list
        }

        // Setup RecyclerView with horizontal layout
        viewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewCart.adapter = ProductAdapter(cartProductList)

        // Add snapping behavior to RecyclerView for smooth scrolling
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(viewCart)

        // Calculate and display totals
        calculateTotals(subtotalTxt, deliveryTxt, taxTxt, totalTxt)

        // Set click listener for back button
        backBtn.setOnClickListener {
            finish()
        }

        // Set click listener for Remove All Products button
        removeAllButton.setOnClickListener {
            removeAllProducts()
            viewCart.adapter?.notifyDataSetChanged() // Notify adapter that the data has changed
            calculateTotals(subtotalTxt, deliveryTxt, taxTxt, totalTxt) // Update totals after removal
        }

        // Set click listener for Checkout button
        checkoutButton.setOnClickListener {
            // Save the cart items to order history before checkout
            saveOrderData(cartProductList)

            // Clear the cart after successful checkout
            removeAllProducts()
            viewCart.adapter?.notifyDataSetChanged() // Update cart view

            // Start the MyOrders activity to view the order history
            val intent = Intent(this, MyOders::class.java)
            startActivity(intent)
        }
    }

    // Remove all products from the cart
    private fun removeAllProducts() {
        cartProductList.clear() // Clear the product list
        saveCartData() // Save the updated cart list
    }

    // Save the cart data to SharedPreferences
    private fun saveCartData() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(cartProductList)
        editor.putString(cartKey, json)
        editor.apply()
    }

    // Load the cart data from SharedPreferences
    private fun loadCartData() {
        val json = sharedPreferences.getString(cartKey, null)
        if (json != null) {
            val type = object : TypeToken<MutableList<Product>>() {}.type
            val savedCartList: MutableList<Product> = gson.fromJson(json, type)
            cartProductList.clear()
            cartProductList.addAll(savedCartList)
        }
    }

    // Save order data to SharedPreferences
    private fun saveOrderData(orderList: List<Product>) {
        val ordersJson = sharedPreferences.getString(ordersKey, null)
        val orders: MutableList<Product> = if (ordersJson != null) {
            val type = object : TypeToken<MutableList<Product>>() {}.type
            gson.fromJson(ordersJson, type)
        } else {
            mutableListOf()
        }
        // Add the new order list to existing orders
        orders.addAll(orderList)

        // Save updated orders to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString(ordersKey, gson.toJson(orders))
        editor.apply()
    }

    // Calculate subtotal, tax, and total
    private fun calculateTotals(subtotalTxt: TextView, deliveryTxt: TextView, taxTxt: TextView, totalTxt: TextView) {
        val subtotal = cartProductList.sumOf { it.price }
        val deliveryFee = if (subtotal > 0) 0 else 0 // Assuming free delivery for now
        val tax = (subtotal * 0.1).toInt() // Assuming 10% tax
        val total = subtotal + deliveryFee + tax

        // Set the calculated values to the respective TextViews
        subtotalTxt.text = "Rs. $subtotal"
        deliveryTxt.text = "Rs. $deliveryFee"
        taxTxt.text = "Rs. $tax"
        totalTxt.text = "Rs. $total"
    }
}
