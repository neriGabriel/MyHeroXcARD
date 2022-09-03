package com.example.myxherocard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.myxherocard.model.FirebaseUserLiveData

class UserViewModel: ViewModel() {

    private val _authenticationState = FirebaseUserLiveData().map { currentUser ->
        if(currentUser != null ) AuthenticationState.AUTHENTICATED else AuthenticationState.UNAUTHENTICATED
    }

    val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState
}


enum class AuthenticationState {
    AUTHENTICATED, UNAUTHENTICATED, INVALID
}