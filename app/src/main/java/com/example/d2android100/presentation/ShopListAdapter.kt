package com.example.d2android100.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.d2android100.R
import com.example.d2android100.domain.ShopItem

class ShopListAdapter: ListAdapter<ShopItem, ShopListAdapter.VH>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener : ((ShopItem)-> Unit)? = null
    var onShopItemClickListener:((ShopItem)->Unit)? = null

    var count = 0
    inner class VH(view: View) : RecyclerView.ViewHolder(view){
        val count  = view.findViewById<TextView>(R.id.id1)
        val name  = view.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        var layout = when(viewType){
            ENABLED_VIEW -> R.layout.item_view_enabled
            DISABLED_VIEW -> R.layout.item_view_disabled
            else -> throw RuntimeException("Unkown viewType $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.d("ShopListAdapter", "onBindViewHolder: count: ${++count}")
        val shopItem = getItem(position)
        var enabled = if (shopItem.enabled){
            "Active"
        }else{
            "Non Active"
        }
        holder.name.text = "${shopItem.item_name} $enabled"

        holder.count.text = shopItem.count.toString()

        holder.itemView.setOnLongClickListener{
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }


    }

//    override fun onViewRecycled(holder: VH) {
//        holder.name.text = ""
//        holder.count.text = ""
//        holder.name.setTextColor(ContextCompat.getColor(holder.itemView.context,android.R.color.white))
//        super.onViewRecycled(holder)
//    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (item.enabled){
            ENABLED_VIEW
        }else{
            DISABLED_VIEW
        }
}






    companion object {
        const val ENABLED_VIEW = 0
        const val DISABLED_VIEW = 1

        const val POOL_SIZE = 15
    }
}
