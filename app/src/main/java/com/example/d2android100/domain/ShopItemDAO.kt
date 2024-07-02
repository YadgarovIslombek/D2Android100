package com.example.d2android100.domain

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.d2android100.data.ShopItemDbModel

@Dao
interface ShopItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItem: ShopItem)
    @Update
    fun editShopItem(shopItem: ShopItem)

    @Query("DELETE FROM shop_items WHERE id = :id")
    fun deleteShopItem(id: Int)
    @Query("Select * from shop_items")
    fun getShopItemList(): LiveData<List<ShopItem>>
    @Query("Select * from shop_items where id = :id LIMIT 1")
    fun getShopItemById(id:Int): ShopItem
}