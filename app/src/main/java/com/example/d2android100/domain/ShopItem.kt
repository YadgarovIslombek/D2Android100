package com.example.d2android100.domain

data class ShopItem(
    val item_name:String,
    val count:Int,
    val enabled:Boolean,
    var id:Int= UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}