package com.example.mathsolver.rest

import com.example.mathsolver.Solution
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestService {
    @GET("api/v2/{type}/{formula}")
    fun getResult(@Path("type") type: String, @Path("formula") formula: String): Call<Solution>
}