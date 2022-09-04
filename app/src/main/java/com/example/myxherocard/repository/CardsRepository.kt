package com.example.myxherocard.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.myxherocard.api.connection.CardService
import com.example.myxherocard.database.CardDatabase
import com.example.myxherocard.database.asDatabaseModel
import com.example.myxherocard.database.asDomainModel
import com.example.myxherocard.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

class CardsRepository(private val database: CardDatabase, private val cardService: CardService) {

    private companion object {
        private var TAG = CardsRepository::class.simpleName
    }

    val cards: LiveData<List<Card>> = Transformations.map(
        database.cardDAO.getCards()) {
            it.asDomainModel()
    }


    suspend fun updateFavoriteByCard(card: Card) {
        withContext(Dispatchers.IO) {
            try {
                database.cardDAO.update(card.asDatabaseModel())
            } catch (error: Exception) {
                Log.w(TAG, "Not able to update favorite option")
            }
        }
    }

    suspend fun getAllCards() {
        withContext(Dispatchers.IO) {
            try {
                val newCards = cardService.getAllCards().cards
                val insertCards = database.cardDAO.insert(newCards.asDatabaseModel())
                Log.d(TAG, "insert the following cards: $insertCards")
            } catch (error: Exception) {
                Log.w(TAG, "Erro while getting all cards ${error.message}")
            }
        }
    }
}