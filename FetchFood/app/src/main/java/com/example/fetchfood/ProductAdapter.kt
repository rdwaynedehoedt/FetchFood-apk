package com.example.fetchfood

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchfood.models.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Initialize views using findViewById
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productImage: ImageView = itemView.findViewById(R.id.productImage)

        fun bind(product: Product) {
            // Binding the product's data to the UI
            productName.text = product.name
            productPrice.text = "Rs. ${product.price}"


            if (!product.imageURL.isNullOrEmpty()) {
                // Load image using Picasso only if URL is valid
                Picasso.get()
                    .load(product.imageURL)
                    .placeholder(R.drawable.placeholder) // Add a placeholder image
                    .error(R.drawable.error_image) // Add an error image in case the URL is not valid
                    .into(productImage)
            } else {
                // If image URL is empty or null, show an error or placeholder image
                productImage.setImageResource(R.drawable.error_image)
            }

            // Set OnClickListener for item click to navigate to ProductDetailActivity
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ProductDetailsActivity::class.java)
                // Pass product data to ProductDetailActivity using intent extras
                intent.putExtra("productName", product.name)
                intent.putExtra("productPrice", product.price)
                intent.putExtra("productImage", product.imageURL)
                intent.putExtra("productDescription", product.description)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount() = productList.size
}
