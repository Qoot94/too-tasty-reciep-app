package com.example.too_tasty_reciep_app

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APInterface {

    @GET("recipes/")
    fun getRecipes(): Call<ArrayList<reciepeItem>>

    @POST("recipes/")
    fun addRecipes(
        @Body data: reciepeItem
    ): Call<ArrayList<reciepeItem>>
}