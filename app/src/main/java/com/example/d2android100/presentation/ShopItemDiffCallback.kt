package com.example.d2android100.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.d2android100.domain.ShopItem

class ShopItemDiffCallback:DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}