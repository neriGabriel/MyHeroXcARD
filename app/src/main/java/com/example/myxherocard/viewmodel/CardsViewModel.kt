package com.example.myxherocard.viewmodel

import androidx.lifecycle.*
import com.example.myxherocard.model.Card
import com.example.myxherocard.repository.CardsRepository
import kotlinx.coroutines.launch

class CardsViewModel(private val cardsRepository: CardsRepository): ViewModel() {

    val cards: LiveData<List<Card>>
        get() = cardsRepository.cards

    init {
        refreshCards()
    }

    private fun refreshCards() {
        viewModelScope.launch {
            cardsRepository.getAllCards()
        }
    }

    fun updateFavorite(card: Card) {
        viewModelScope.launch {
            cardsRepository.updateFavoriteByCard(card)
        }
    }
}