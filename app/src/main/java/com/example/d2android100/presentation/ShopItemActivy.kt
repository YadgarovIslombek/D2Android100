package com.example.d2android100.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.d2android100.R
import com.example.d2android100.databinding.ActivityShopItemActivyBinding
import com.example.d2android100.domain.ShopItem

class ShopItemActivy : AppCompatActivity() {
    //    lateinit var viewModel: ShopItemViewModel
//    private var status = UNKOWN
//    private var shopItemID = ShopItem.UNDEFINED_ID
    lateinit var binding: ActivityShopItemActivyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemActivyBinding.inflate(layoutInflater)
        // parseIntent()
        // viewModel  = ViewModelProvider(this)[ShopItemViewModel::class.java]
        setContentView(binding.root)
//        showView()
//        binding.name.addTextChangedListener(object:TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                Log.d("TAG", "beforeTextChanged: ")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetInputNameError()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                Log.d("TAG", "afterTextChanged: ")
//            }
//
//        })
//        binding.count.addTextChangedListener(object:TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                Log.d("TAG", "beforeTextChanged: ")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetInputCountError()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                Log.d("TAG", "afterTextChanged: ")
//            }
//
//        })
//
//        viewModel.shouldCloseScreen.observe(this){
//            finish()
//        }
//    }
//
//
//    private fun showView() {
//        when (status) {
//            ADD -> launchAddStatus()
//            EDIT -> launchEditStatus()
//        }
//        viewModel.errorInputName.observe(this) {
//            val mess = if (it) {
//                getString(R.string.error_input_name)
//            } else {
//                null
//            }
//            binding.name1.error = mess
//        }
//        viewModel.errorInputCount.observe(this) {
//            val mess = if (it) {
//                getString(R.string.error_input_count)
//            } else {
//                null
//            }
//            binding.count1.error = mess
//        }
//
//    }
//    private fun launchEditStatus() {
//        val id = intent.getIntExtra(SHOP_ID, ShopItem.UNDEFINED_ID)
//        viewModel.getShopById(id)
//         viewModel.shopItem.observe(this@ShopItemActivy){
//             binding.name.setText(it.item_name.toString())
//             binding.count.setText(it.count.toString())
//         }
//        binding.apply {
//            save.setOnClickListener {
//                viewModel.editShopItem(name.text.toString(),count.text.toString())
//            }
//        }
//    }
//
//    private fun launchAddStatus() {
//        binding.apply {
//            save.setOnClickListener {
//                viewModel.addShopItem(name.text.toString(),count.text.toString())
//            }
//        }
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(STATUS)) {
//            throw RuntimeException("Hech qanday status kelmadi")
//        }
//        status = intent.getStringExtra(STATUS).toString()
//        if (status != ADD && status != EDIT) {
//            throw RuntimeException("status mos emas $status")
//        }
//
//        if (status == EDIT) {
//            if (!intent.hasExtra(SHOP_ID)) {
//                throw RuntimeException("id yoqqu")
//            }
//            shopItemID = intent.getIntExtra(SHOP_ID, ShopItem.UNDEFINED_ID)
//
//        }
//
   }
//
    companion object {
        const val STATUS = "status"
        const val ADD = "add"
        const val EDIT = "edit"
        const val SHOP_ID = "id"
        const val UNKOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivy::class.java)
            intent.putExtra(STATUS, ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, id: Int): Intent {
            val intent = Intent(context, ShopItemActivy::class.java)
            intent.putExtra(STATUS, EDIT)
            intent.putExtra(SHOP_ID, id)
            return intent
        }
    }

}