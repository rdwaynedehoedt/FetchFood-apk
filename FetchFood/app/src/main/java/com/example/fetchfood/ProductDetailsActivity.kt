package com.example.fetchfood

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get data from intent
        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("productDescription")
        val productPrice = intent.getIntExtra("productPrice", 0)
        val productImageURL = intent.getStringExtra("productImageURL")

        // Initialize views
        val titleTxt: TextView = findViewById(R.id.titleTxt)
        val descriptionTxt: TextView = findViewById(R.id.descriptionTxt)
        val priceTxt: TextView = findViewById(R.id.priceTxt)
        val productImage: ImageView = findViewById(R.id.img)
        val backBtn: ImageView = findViewById(R.id.backBtn)

        // Set data to views
        titleTxt.text = productName
        descriptionTxt.text = productDescription
        priceTxt.text = "Rs. $productPrice"
        Picasso.get().load(productImageURL).into(productImage)

        // Set OnClickListener for the back button
        backBtn.setOnClickListener {
            finish() // Closes the activity and returns to the previous screen
        }
    }
}
