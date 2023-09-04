package com.example.d2android100.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.d2android100.R
import com.example.d2android100.databinding.ActivityMainBinding
import com.example.d2android100.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel
    lateinit var binding: ActivityMainBinding
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        myViewModel.shopList.observe(this) {
//            Log.d("TAG", "onCreate: $it ")
//            if (count == 0) {
//
//                val shopItem = it[0]
//                myViewModel.enabled(shopItem)
//                count++
//            }
            showView(it)

        }


    }


    private fun showView(list: List<ShopItem>) {
        binding.ll.removeAllViews()
        for (shopItem in list) {
            val id_l = if (shopItem.enabled) {
                R.layout.item_view_enabled
            } else {
                R.layout.item_view_disabled
            }
                val view = LayoutInflater.from(this).inflate(id_l,binding.ll,false)
            val count = view.findViewById<TextView>(R.id.id1)
            val name = view.findViewById<TextView>(R.id.name)

            count.text = shopItem.count.toString()
            name.text = shopItem.item_name.toString()
            view.setOnLongClickListener{
                myViewModel.enabled(shopItem)
                true
            }
            view.setOnClickListener {
                myViewModel.deleteShopItem(shopItem)
            }
            binding.ll.addView(view)
        }
    }
}