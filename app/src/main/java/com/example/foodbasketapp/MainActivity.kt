package com.example.foodbasketapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartShopping = findViewById<Button>(R.id.btnStartShopping)
        btnStartShopping.setOnClickListener {
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }
    }
}