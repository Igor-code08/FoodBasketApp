package com.example.foodbasketapp

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import com.example.foodbasketapp.CheckoutActivity as CheckoutActivity1

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val cartItems = intent.getParcelableArrayListExtra<Product>("cart") ?: emptyList()

        val receiptDetails = StringBuilder()
        var totalPrice = 0.0

        for (item in cartItems) {
            receiptDetails.append("${item.name}: ${item.price} ₽\n")
            totalPrice += item.price
        }

        receiptDetails.append("\nИтого: $totalPrice ₽")

        findViewById<TextView>(R.id.tvReceiptDetails).text = receiptDetails.toString()

        findViewById<Button>(R.id.btnFinishShopping).setOnClickListener {
            Toast.makeText(this, "Спасибо за покупку!", Toast.LENGTH_SHORT).show()
            finishAffinity() // Закрыть все активности и выйти из приложения
        }
    }
}