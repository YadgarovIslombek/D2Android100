package com.example.d2android100.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.d2android100.R
import com.example.d2android100.databinding.ActivityShopItemActivyBinding
import com.example.d2android100.domain.ShopItem

class ShopItemActivy : AppCompatActivity(),ShopItemFragment.editListener {

    lateinit var binding: ActivityShopItemActivyBinding
    private var screenStatus= UNKOWN
    private var shopItemId= -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopItemActivyBinding.inflate(layoutInflater)

        // viewModel  = ViewModelProvider(this)[ShopItemViewModel::class.java]
        setContentView(binding.root)
        parseIntent()
        launchRightStatus()
    }
    private fun launchRightStatus() {
        val fragment = when (screenStatus) {
            EDIT -> ShopItemFragment.newIntanseItemEdit(shopItemId)
            ADD -> ShopItemFragment.newIntanseItemAdd()
            else -> throw RuntimeException("Qaysi statusli scrennligi topilmadi $screenStatus")
        }
        supportFragmentManager.beginTransaction().add(R.id.container_id,fragment).commit()

    }

    private fun parseIntent() {
        if (!intent.hasExtra(STATUS)) {
            throw RuntimeException("parametrlar topilmadi")
        }
        val status = intent.getStringExtra(STATUS)
        if (status != ADD && status != EDIT) {
            throw RuntimeException("Status xato $status")
        }
        screenStatus = status
        if (status == EDIT) {
            if (!intent.hasExtra(SHOP_ID)) {
                throw RuntimeException("EDIT STATUSDA ID KELMADI")
            }
            shopItemId = intent.getIntExtra(SHOP_ID, -1)

        }
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

    override fun onEditListenerFinished() {
        Toast.makeText(this@ShopItemActivy, "Success", Toast.LENGTH_SHORT).show()
        finish()
    }

}