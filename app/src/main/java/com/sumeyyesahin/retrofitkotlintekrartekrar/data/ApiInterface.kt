package com.sumeyyesahin.retrofitkotlintekrartekrar.data

import com.sumeyyesahin.retrofitkotlintekrartekrar.models.AllProductData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    suspend fun getProducts(): Response<AllProductData>
}