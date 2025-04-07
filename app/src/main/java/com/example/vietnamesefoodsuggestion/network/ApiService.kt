package com.example.vietnamesefoodsuggestion.network

import com.example.vietnamesefoodsuggestion.data.PredictRequest
import com.example.vietnamesefoodsuggestion.data.PredictResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/predict")
    fun predictFood(@Body request: PredictRequest): Call<PredictResponse>
}