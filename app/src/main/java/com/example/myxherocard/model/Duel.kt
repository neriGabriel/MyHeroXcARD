package com.example.myxherocard.model

import com.google.gson.annotations.SerializedName


data class Duel(
    @SerializedName("relevant") var relevant: Boolean? = null,
    @SerializedName("constructed") var constructed: Boolean? = null
)