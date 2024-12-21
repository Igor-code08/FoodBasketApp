package com.example.foodbasketapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClicked: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        val tvProductPrice = itemView.findViewById<TextView>(R.id.tvProductPrice)

        fun bind(product: Product) {
            tvProductName.text = product.name
            tvProductPrice.text = "${product.price} ₽"

            itemView.setOnClickListener {
                onItemClicked(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}