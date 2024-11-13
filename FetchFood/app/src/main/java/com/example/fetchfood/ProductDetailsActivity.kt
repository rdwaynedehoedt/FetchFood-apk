package com.example.fetchfood

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.example.fetchfood.models.Product

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("productDescription")
        val productPrice = intent.getIntExtra("productPrice", 0)
        val productImageURL = intent.getStringExtra("productImage")

        // Initialize views
        val titleTxt: TextView = findViewById(R.id.titleTxt)
        val descriptionTxt: TextView = findViewById(R.id.descriptionTxt)
        val priceTxt: TextView = findViewById(R.id.priceTxt)
        val productImage: ImageView = findViewById(R.id.img)
        val backBtn: ImageView = findViewById(R.id.backBtn)
        val addToCartBtn: ImageView = findViewById(R.id.addToCartBtn)
        val buyButton: TextView = findViewById(R.id.buyNowBtn)

        // Set data to views
        titleTxt.text = productName
        descriptionTxt.text = productDescription
        priceTxt.text = "Rs. $productPrice"
        Picasso.get().load(productImageURL).into(productImage)

        // Set OnClickListener for the back button
        backBtn.setOnClickListener {
            finish()
        }

        // Set OnClickListener for Add to Cart button
        addToCartBtn.setOnClickListener {
            val product = Product(
                name = productName ?: "",
                description = productDescription ?: "",
                price = productPrice,
                imageURL = productImageURL ?: ""
            )

            // Pass the product to the cart activity
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("productName", product.name)
            intent.putExtra("productPrice", product.price)
            intent.putExtra("productImage", product.imageURL)
            intent.putExtra("productDescription", product.description)
            startActivity(intent)
        }
        // Set OnClickListener for Add to Cart button
        buyButton.setOnClickListener {
            val product = Product(
                name = productName ?: "",
                description = productDescription ?: "",
                price = productPrice,
                imageURL = productImageURL ?: ""
            )

            // Pass the product to the cart activity
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("productName", product.name)
            intent.putExtra("productPrice", product.price)
            intent.putExtra("productImage", product.imageURL)
            intent.putExtra("productDescription", product.description)
            startActivity(intent)
        }
    }
}
