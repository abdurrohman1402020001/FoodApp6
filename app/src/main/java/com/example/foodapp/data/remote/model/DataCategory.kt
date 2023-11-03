package com.example.foodapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class
DataCategory(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("nama")
    val nama: String
)