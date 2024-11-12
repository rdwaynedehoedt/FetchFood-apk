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
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)

        fun bind(product: Product) {
            // Binding the product's data to the UI
            productName.text = product.name
            productPrice.text = "$${product.price}"
            // Load image using Picasso
            Picasso.get().load(product.imageURL).into(productImage)

            // Set OnClickListener for item click to navigate to ProductDetailActivity
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ProductDetailsActivity::class.java)
                // Pass product data to ProductDetailActivity using intent extras
                intent.putExtra("productName", product.name)
                intent.putExtra("productPrice", product.price)
                intent.putExtra("productImage", product.imageURL) // This should match the key used to retrieve it
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
