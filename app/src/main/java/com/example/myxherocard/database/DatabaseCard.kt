package com.example.myxherocard.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myxherocard.model.Card

@Entity(tableName = "cards")
data class DatabaseCard(
    @PrimaryKey val id: Int,
    val collectible: Int? = null,
    var slug: String? = null,
    var classId: Int? = null,
    var spellSchoolId: Int? = null,
    var cardTypeId: Int? = null,
    var cardSetId: Int? = null,
    var rarityId: Int? = null,
    var artistName: String? = null,
    var manaCost: Int? = null,
    var name: String? = null,
    var text: String? = null,
    var image: String? = null,
    var imageGold: String? = null,
    var flavorText: String? = null,
    var cropImage: String? = null,
    var isFavorite: Int = 0
)

fun List<DatabaseCard>.asDomainModel(): List<Card> {
    return map {
        Card(
            id = it.id,
            collectible = it.collectible,
            slug = it.slug,
            classId = it.classId,
            spellSchoolId = it.spellSchoolId,
            cardTypeId = it.cardTypeId,
            cardSetId = it.cardSetId,
            rarityId = it.rarityId,
            artistName = it.artistName,
            manaCost = it.manaCost,
            name = it.name,
            text = it.text,
            image = it.image,
            flavorText =  it.flavorText,
            cropImage = it.cropImage,
            isFavorite = it.isFavorite
        )
    }
}

fun List<Card>.asDatabaseModel(): List<DatabaseCard> {
    return map {
        DatabaseCard(
            id = it.id!!,
            collectible = it.collectible,
            slug = it.slug,
            classId = it.classId,
            spellSchoolId = it.spellSchoolId,
            cardTypeId = it.cardTypeId,
            cardSetId = it.cardSetId,
            rarityId = it.rarityId,
            artistName = it.artistName,
            manaCost = it.manaCost,
            name = it.name,
            text = it.text,
            image = it.image,
            flavorText =  it.flavorText,
            cropImage = it.cropImage,
            isFavorite = it.isFavorite
        )
    }
}

fun Card.asDatabaseModel(): DatabaseCard {
    return DatabaseCard(
        id = this.id!!,
        collectible = this.collectible,
        slug = this.slug,
        classId = this.classId,
        spellSchoolId = this.spellSchoolId,
        cardTypeId = this.cardTypeId,
        cardSetId = this.cardSetId,
        rarityId = this.rarityId,
        artistName = this.artistName,
        manaCost = this.manaCost,
        name = this.name,
        text = this.text,
        image = this.image,
        flavorText =  this.flavorText,
        cropImage = this.cropImage,
        isFavorite = this.isFavorite
    )
}