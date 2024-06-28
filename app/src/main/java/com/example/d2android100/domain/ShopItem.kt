package com.example.d2android100.domain


data class ShopItem(
    val name:String,
    val count:Int,
    val enabled:Boolean,
    var _id:Int= UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}