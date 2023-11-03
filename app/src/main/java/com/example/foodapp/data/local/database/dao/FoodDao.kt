package com.example.foodapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.foodapp.data.local.database.entity.FoodEntity

@Dao
interface FoodDao {
    @Insert
    suspend fun addFood(foodEntity: FoodEntity)

    @Delete
    suspend fun removeFood(foodEntity: FoodEntity)

    @Query("SELECT * FROM food ORDER BY id DESC")
    suspend fun getAllFoodCart(): List<FoodEntity>

    @Query("SELECT SUM(price) from food ")
    suspend fun getTotalFood(): Int

    @Query("UPDATE food SET stock = :stock, price = :price WHERE id = :id ")
    suspend fun updateStock(stock: Int, id: Int, price: Int)

    @Query("DELETE FROM food")
    suspend fun deleteAll()
}