package com.example.foodapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name: String,
    var image:String,
    var price:String,
    var stock:Int
)
