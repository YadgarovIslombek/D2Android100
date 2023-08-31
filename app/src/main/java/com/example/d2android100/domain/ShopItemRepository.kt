package com.example.d2android100.domain

interface ShopItemRepository {
    fun addShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
    fun getShopItemList():List<ShopItem>
    fun getShopItemById(id:Int):ShopItem
}