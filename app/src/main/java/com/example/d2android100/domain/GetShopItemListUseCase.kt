package com.example.d2android100.domain

import androidx.lifecycle.LiveData

    class GetShopItemListUseCase(private val repository: ShopItemRepository) {
    fun getShopItemList():LiveData<List<ShopItem>>{
        return repository.getShopItemList()
    }
}