package com.example.d2android100.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.d2android100.R

class ShopItemActivy : AppCompatActivity() {
    lateinit var viewModel: ShopItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item_activy)
        val status = intent.getStringExtra("STATUS")
        Log.d("TAG", "onCreate: $status")

    }

}