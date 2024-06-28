package com.example.d2android100.domain

class EditShopItemUseCase(private val repository: ShopItemRepository) {
     fun editShopItem(shopItem: ShopItem){
        repository.editShopItem(shopItem)
    }
}