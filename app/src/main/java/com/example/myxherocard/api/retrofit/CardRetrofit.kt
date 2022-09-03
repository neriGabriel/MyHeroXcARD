package com.example.myxherocard.api.retrofit

import com.example.myxherocard.Constants.BASE_URL
import com.example.myxherocard.api.connection.CardService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CardRetrofit {

    fun getCardApi(): CardService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CardService::class.java)
    }
}