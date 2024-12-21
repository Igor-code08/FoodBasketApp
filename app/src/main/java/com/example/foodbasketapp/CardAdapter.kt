package com.example.foodbasketapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartItems: List<Product>,
    private val onRemoveItemClicked: (Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        private val tvProductPrice = itemView.findViewById<TextView>(R.id.tvProductPrice)
        private val btnRemove = itemView.findViewById<Button>(R.id.btnRemove)

        fun bind(product: Product) {
            tvProductName.text = product.name
            tvProductPrice.text = "${product.price} ₽"

            btnRemove.setOnClickListener {
                onRemoveItemClicked(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount(): Int = cartItems.size
}