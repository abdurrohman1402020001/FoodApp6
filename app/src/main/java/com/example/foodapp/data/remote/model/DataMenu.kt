package com.example.foodapp.data.remote.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class DataMenu(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("alamat_resto")
    val alamatResto: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("harga_format")
    val hargaFormat: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("nama")
    val nama: String
)