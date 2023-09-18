package com.example.d2android100.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.d2android100.data.db.AppDatabase
import com.example.d2android100.domain.ShopItem
import com.example.d2android100.domain.ShopItemRepository

class ShopListItemRepositoryImpl(application: Application) : ShopItemRepository {
    //    private val list = sortedSetOf(Comparator<ShopItem> { o1, o2 -> o1.id.compareTo(o2.id) })
//    private var autoIncrement = 0;
    private val ld = MutableLiveData<List<ShopItem>>()

    private val mapper = Mapper()
    private val db: AppDatabase = AppDatabase.getInstance(application)


    //    init {
//        for (i in 0 .. 1000){
//            val shop  = ShopItem("Item $i",i,kotlin.random.Random.nextBoolean())
//            addShopItem(shop)
//        }
//    }
    override  fun addShopItem(shopItem: ShopItem) {
            db.shopItemDao()
                .addShopItem(shopItem)

    }

    override fun editShopItem(shopItem: ShopItem) {

        db.shopItemDao()
                .addShopItem(shopItem)

    }

    override fun deleteShopItem(shopItem: ShopItem) {
//        list.remove(shopItem)
        db.shopItemDao().deleteShopItem(shopItem.id)
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> {

        return  MediatorLiveData<List<ShopItem>>().apply {
            addSource(db.shopItemDao().getShopItemList()) {
                value = it
            }
        }
    }

    override fun getShopItemById(id: Int): ShopItem =
        db.shopItemDao().getShopItemById(id)


//    private fun update_ld() {
//        ld.value = db.shopItemDao().getShopItemList().value?.toList()
//    }
}