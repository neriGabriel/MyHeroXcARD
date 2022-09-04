package com.example.myxherocard.api.connection

import com.example.myxherocard.model.Cards
import retrofit2.http.GET
import retrofit2.http.Query

interface CardService {
    @GET("cards")
    suspend fun getAllCards(
        @Query("locale") locale: String = "en_US",
        @Query("access_token") accessToken: String = "US1Hh42dStV3Pgw5XpBfA8ZkN6U6v548hF"
    ): Cards

}