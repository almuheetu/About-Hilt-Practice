package com.example.retrofitproject.reposatories

import com.example.retrofitproject.model.EmployeeResponse
import com.example.retrofitproject.networks.ApiClient
import com.example.retrofitproject.networks.ApiInterface
import javax.inject.Inject



class EmployeeRepository @Inject constructor()  {
    suspend fun getEmployee(): EmployeeResponse? {
        val employeeApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return employeeApi.getEmployee().body()
    }
}