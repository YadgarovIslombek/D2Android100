package com.example.d2android100.domain

class GetShopItemListUseCase(private val repository: ShopItemRepository) {
    fun getShopItemList():List<ShopItem>{
        return repository.getShopItemList()
    }
}