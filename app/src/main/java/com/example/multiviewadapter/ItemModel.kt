package com.example.multiviewadapter

data class ItemModel(
    val viewType: Int,
    var title :String="",
    var Url: List<String> = arrayListOf(),
)
