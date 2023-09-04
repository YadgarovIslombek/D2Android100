package com.example.d2android100.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.d2android100.domain.ShopItem
import com.example.d2android100.domain.ShopItemRepository

object ShopListItemRepositoryImpl : ShopItemRepository {
    private val list = sortedSetOf(Comparator<ShopItem> { o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoIncrement = 0;
    private val ld = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 .. 10000){
            val shop  = ShopItem("Item $i",i,true)
            addShopItem(shop)
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrement++
        }
        list.add(shopItem)
        update_ld()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val id = shopItem.id;
        val oldId = getShopItemById(id)
        list.remove(oldId)
        addShopItem(shopItem)

    }

    override fun deleteShopItem(shopItem: ShopItem) {
        list.remove(shopItem)
        update_ld()
    }

    override fun getShopItemList():LiveData<List<ShopItem>> {
        return ld
    }

    override fun getShopItemById(id: Int): ShopItem {
        return list.find {
            it.id == id
        } ?: throw RuntimeException("$id bu idli tovar topilmadi")
    }
   private fun update_ld(){
        ld.value = list.toList()
    }
}