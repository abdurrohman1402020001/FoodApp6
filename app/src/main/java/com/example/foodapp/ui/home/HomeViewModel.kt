package com.example.foodapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.data.remote.api.RestfulApi

import com.example.foodapp.data.remote.model.CategoryEntity
import com.example.foodapp.data.remote.model.DataCategory
import com.example.foodapp.data.remote.model.DataMenu
import com.example.foodapp.data.remote.model.MenuEntity
import dagger.hilt.android.lifecycle.HiltViewModel


import retrofit2.Callback

import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val restfulApi: RestfulApi) : ViewModel() {
    var liveDataMenuMenu: MutableLiveData<List<DataMenu>> = MutableLiveData()
    var liveDataCategory: MutableLiveData<List<DataCategory>> = MutableLiveData()


    fun getMenu() {
        restfulApi.getAllMenu()
            .enqueue(object : Callback<MenuEntity> {
                override fun onResponse(
                    call: retrofit2.Call<MenuEntity>,
                    response: Response<MenuEntity>
                ) {
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        liveDataMenuMenu.postValue(userResponse!!.data)
                    } else {
                        Log.d("info", response.body().toString())
                    }
                }

                override fun onFailure(call: retrofit2.Call<MenuEntity>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                }
            })


    }

    fun getCategories() {
        restfulApi.getCategory()
            .enqueue(object : Callback<CategoryEntity> {
                override fun onResponse(
                    call: retrofit2.Call<CategoryEntity>,
                    response: Response<CategoryEntity>
                ) {
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        liveDataCategory.postValue(userResponse!!.data)
                    } else {
                        Log.d("info", response.body().toString())
                    }
                }

                override fun onFailure(call: retrofit2.Call<CategoryEntity>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                }
            })
    }
}