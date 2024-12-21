package com.example.foodbasketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity<Bundle> : AppCompatActivity() {
    private lateinit var cartItems: MutableList<Product>
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Получаем данные из Intent
        cartItems = intent.getParcelableArrayListExtra("cart") ?: mutableListOf()

        val recyclerView = findViewById<RecyclerView>(R.id.rvCartItems)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CartAdapter(cartItems) { product ->
            removeItemFromCart(product)
        }
        recyclerView.adapter = adapter

        updateTotalPrice()

        findViewById<Button>(R.id.btnCheckout).setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putParcelableArrayListExtra("cart", ArrayList(cartItems))
            startActivity(intent)
        }
    }

    private fun removeItemFromCart(product: Product) {
        cartItems.remove(product)
        adapter.notifyDataSetChanged()
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        val totalPrice = cartItems.sumOf { it.price }
        findViewById<TextView>(R.id.tvTotalPrice).text = "Итого: $totalPrice ₽"
    }
}
