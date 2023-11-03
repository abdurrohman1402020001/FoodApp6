package com.example.foodapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class MenuEntity(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<DataMenu>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)