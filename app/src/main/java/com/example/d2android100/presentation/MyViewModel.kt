package com.example.d2android100.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.d2android100.data.ShopListItemRepositoryImpl
import com.example.d2android100.domain.DeleteShopItemUseCase
import com.example.d2android100.domain.EditShopItemUseCase
import com.example.d2android100.domain.GetShopItemListUseCase
import com.example.d2android100.domain.ShopItem

class MyViewModel:ViewModel() {

    val repository = ShopListItemRepositoryImpl
    val getShopItemListUseCase  = GetShopItemListUseCase(repository)
    val editShopItemUseCase = EditShopItemUseCase(repository)
    val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopItemListUseCase.getShopItemList()
    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)

    }
    fun enabled(shopItem: ShopItem){
        val newValue = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newValue)
    }



}