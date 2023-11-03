package com.example.foodapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("qty")
    val qty: Int
)