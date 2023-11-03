package com.example.foodapp.ui.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.local.database.dao.FoodDao
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.data.remote.api.RestfulApi
import com.example.foodapp.data.remote.model.Order
import com.example.foodapp.data.remote.model.OrderEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val restfulApi: RestfulApi,
    private val foodDao: FoodDao
) : ViewModel() {
    // TODO: Implement the ViewModel
    private val dataFood: MutableLiveData<List<FoodEntity>> = MutableLiveData()

    var dataOrder: MutableLiveData<List<Order>> = MutableLiveData()

    fun postOrder(catatan: String, harga: Int, nama: String, jumlah: Int) {
        restfulApi.postOrder(catatan, harga, nama, jumlah)
            .enqueue(object : Callback<OrderEntity> {
                override fun onResponse(
                    call: retrofit2.Call<OrderEntity>,
                    response: Response<OrderEntity>
                ) {
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        dataOrder.postValue(userResponse!!.orders)
                    } else {
                        Log.d("info", response.body().toString())
                    }
                }

                override fun onFailure(call: retrofit2.Call<OrderEntity>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                }
            })


    }

    fun getFoodObserver(): MutableLiveData<List<FoodEntity>> {
        return dataFood
    }

    fun getAllData() = viewModelScope.launch {
        dataFood.postValue(foodDao.getAllFoodCart())
    }

    fun deleteFood(roomEntity: FoodEntity) = viewModelScope.launch {
        foodDao.removeFood(roomEntity)
    }

    fun updateCount(stock: Int, id: Int, harga: Int) {
        viewModelScope.launch {
            foodDao.updateStock(stock, id, harga)
        }
    }
}