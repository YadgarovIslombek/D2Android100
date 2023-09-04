package com.example.d2android100.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {
    fun addShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun getShopItemList():LiveData<List<ShopItem>>
    fun getShopItemById(id:Int):ShopItem
}