package com.example.foodapp.data.remote.api

import com.example.foodapp.data.remote.model.CategoryEntity
import com.example.foodapp.data.remote.model.MenuEntity
import com.example.foodapp.data.remote.model.OrderEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestfulApi {
    @GET("listmenu")
    fun getAllMenu(): Call<MenuEntity>
    @GET("category")
    fun getCategory(): Call<CategoryEntity>

    @POST("category")
    fun postOrder(@Body catatan: String,
                  @Body harga:Int,
                  @Body nama:String,
                  @Body jumlah:Int):Call<OrderEntity>

}