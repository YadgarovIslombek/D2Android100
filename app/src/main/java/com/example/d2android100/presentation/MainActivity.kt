package com.example.d2android100.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.d2android100.R
import com.example.d2android100.databinding.ActivityMainBinding
import com.example.d2android100.domain.ShopItem
import com.example.d2android100.presentation.ShopItemActivy.Companion.ADD
import com.example.d2android100.presentation.ShopItemActivy.Companion.STATUS

class MainActivity : AppCompatActivity() {
    private lateinit var myViewModel: MyViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var shopAdapter: ShopListAdapter
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        setupRecylerView()
        myViewModel.shopList.observe(this) {
            shopAdapter.submitList(it)
        }
        binding.fab.setOnClickListener {
            binding.apply {
                if (fragmentContainerView == null){

                    val intent = ShopItemActivy.newIntentAddItem(this@MainActivity)
                    startActivity(intent)
                }else{
                    launchFragment(ShopItemFragment.newIntanseItemAdd(),"add")
                }
            }
        }
    }
    fun launchFragment(fragment: Fragment,s:String){

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,fragment).
            addToBackStack(s).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack("add",0)
    }


    private fun setupRecylerView() {
        shopAdapter = ShopListAdapter()

        shopAdapter.onShopItemLongClickListener = {
            myViewModel.enabled(it)
        }
        shopAdapter.onShopItemClickListener = {
            if (binding.fragmentContainerView == null) {
                val intent = ShopItemActivy.newIntentEditItem(this@MainActivity, it.id)
                startActivity(intent)
            }else{
                launchFragment(ShopItemFragment.newIntanseItemEdit(it.id),"edit")
            }
        }
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopAdapter.currentList[viewHolder.adapterPosition]
                myViewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rec)
        binding.rec.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.ENABLED_VIEW,
            ShopListAdapter.POOL_SIZE
        )
        binding.rec.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.DISABLED_VIEW,
            ShopListAdapter.POOL_SIZE
        )
        binding.rec.adapter = shopAdapter
    }


}