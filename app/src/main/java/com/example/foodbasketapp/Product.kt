package com.example.foodbasketapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

data class Product(val name: String, val price: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble()
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(name)
        p0.writeDouble(price)
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }
}

class ProductListActivity : AppCompatActivity() {

    private val productList = listOf(
        Product("Яблоки", 100.0),
        Product("Хлеб", 50.0),
        Product("Молоко", 70.0),
        Product("Сыр", 200.0)
    )

    private val cart = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val recyclerView = findViewById<RecyclerView>(R.id.rvProductList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ProductAdapter(productList) { product ->
            showAddToCartDialog(product)
        }

        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabGoToCart).setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putParcelableArrayListExtra("cart", ArrayList(cart))
            startActivity(intent)
        }
    }

    private fun showAddToCartDialog(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Добавить в корзину")
            .setMessage("Вы хотите добавить ${product.name} в корзину?")
            .setPositiveButton("Да") { _, _ ->
                cart.add(product)
                Toast.makeText(this, "${product.name} добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Нет", null)
            .show()
    }
}

private fun <Parcelable> Parcelable.putParcelableArrayListExtra(s: String, arrayList: ArrayList<Product>) {

}
