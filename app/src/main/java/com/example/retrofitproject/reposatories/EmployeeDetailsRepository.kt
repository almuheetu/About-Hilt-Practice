package com.example.retrofitproject.reposatories

import com.example.retrofitproject.model.EmployeeResponseItem
import com.example.retrofitproject.networks.ApiClient
import com.example.retrofitproject.networks.ApiInterface

class EmployeeDetailsRepository {
    suspend fun getEmployeeDetails(id: Int): EmployeeResponseItem? {
        val employeeApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return employeeApi.getEmployeeDetails(id).body()
    }
}