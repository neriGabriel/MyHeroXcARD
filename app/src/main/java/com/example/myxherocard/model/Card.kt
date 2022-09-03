package com.example.myxherocard.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Card(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("collectible") var collectible: Int? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("classId") var classId: Int? = null,
    @SerializedName("multiClassIds") var multiClassIds: ArrayList<String> = arrayListOf(),
    @SerializedName("spellSchoolId") var spellSchoolId: Int? = null,
    @SerializedName("cardTypeId") var cardTypeId: Int? = null,
    @SerializedName("cardSetId") var cardSetId: Int? = null,
    @SerializedName("rarityId") var rarityId: Int? = null,
    @SerializedName("artistName") var artistName: String? = null,
    @SerializedName("manaCost") var manaCost: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("imageGold") var imageGold: String? = null,
    @SerializedName("flavorText") var flavorText: String? = null,
    @SerializedName("cropImage") var cropImage: String? = null,
    @SerializedName("keywordIds") var keywordIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("duels") var duels: Duel? = Duel(),
    var isFavorite: Int = 0
) : Serializable