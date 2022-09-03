package com.example.myxherocard.injection

import com.example.myxherocard.api.retrofit.CardRetrofit.getCardApi
import com.example.myxherocard.database.getCardDatabase
import com.example.myxherocard.repository.CardsRepository
import com.example.myxherocard.viewmodel.CardsViewModel
import com.example.myxherocard.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {
    single { getCardApi() }
    single { getCardDatabase(androidContext())}

    factory { CardsRepository(get(), get()) }

    viewModel {
        CardsViewModel(get())
    }

    viewModel {
        UserViewModel()
    }
}
