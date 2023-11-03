package com.example.foodapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.local.database.dao.FoodDao
import com.example.foodapp.data.local.database.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val foodDao: FoodDao) : ViewModel() {
    fun insertData(data: FoodEntity) {
        viewModelScope.launch {
            foodDao.addFood(data)
        }
    }
}