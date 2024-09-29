package com.example.retrofitproject.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.model.EmployeeResponseItem
import com.example.retrofitproject.reposatories.EmployeeDetailsRepository
import kotlinx.coroutines.launch
import java.io.IOException

class EmployeeDetailsViewModel(val employeeDetailsRepository: EmployeeDetailsRepository) : ViewModel() {
    private val _items: MutableLiveData<EmployeeResponseItem?> by lazy {
        MutableLiveData<EmployeeResponseItem?>()
    }
    val items: LiveData<EmployeeResponseItem?> get() = _items

    fun getEmployeeDetails(id: Int) = viewModelScope.launch {
        try {
            _items.value = employeeDetailsRepository.getEmployeeDetails(id)
            Log.d("items", "getEmployeeDetails: $items")

        } catch (e: IOException) {
            Log.d("catch", "getDivision: $e")
            _items.value = null
        }
    }
}