package com.example.d2android100.domain

class GetShopItemByIdUseCase(private val repository: ShopItemRepository) {
    fun getShopItemById(id:Int):ShopItem{
        return repository.getShopItemById(id)
    }
}