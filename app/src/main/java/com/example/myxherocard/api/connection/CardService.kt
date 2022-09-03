package com.example.myxherocard.api.connection

import com.example.myxherocard.model.Cards
import retrofit2.http.GET
import retrofit2.http.Query

interface CardService {
    @GET("cards")
    suspend fun getAllCards(
        @Query("locale") locale: String = "en_US",
        @Query("access_token") accessToken: String = "USjp4VHuEOtX2f31fnPXmx3Iezgnv3xEZ0"
    ): Cards

}