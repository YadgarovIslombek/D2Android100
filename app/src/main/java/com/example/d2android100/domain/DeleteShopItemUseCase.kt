package com.example.d2android100.domain

class DeleteShopItemUseCase(private val repository: ShopItemRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem)
    }
}