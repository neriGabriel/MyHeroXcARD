package com.example.myxherocard.model

import com.google.gson.annotations.SerializedName

data class Cards (
    @SerializedName("cards" ) var cards : List<Card>
)