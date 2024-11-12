package com.example.fetchfood

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
        private val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)

        fun bind(product: Product) {
            // Binding the product's data to the UI
            productName.text = product.name
            productDescription.text = product.description
            productPrice.text = "$${product.price}"
            // Load image using Picasso
            Picasso.get().load(product.imageURL).into(productImage)
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
