package com.example.d2android100.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int= 0,
    val name:String,
    val count:Int,
    val enabled:Boolean,
)

