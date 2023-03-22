package com.example.samplemobius.RecyclerView.api


import com.example.samplemobius.RecyclerView.schema.CryptoApiResponse
import retrofit2.Response
import retrofit2.http.GET


interface CryptoDataApi {
    @GET("assets")
    suspend fun getAssets(): Response<CryptoApiResponse>

}