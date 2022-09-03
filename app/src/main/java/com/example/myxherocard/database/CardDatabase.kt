package com.example.myxherocard.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseCard::class], version = 2, exportSchema = false)
abstract class CardDatabase: RoomDatabase() {
    abstract val cardDAO: CardDAO
}

fun getCardDatabase(context: Context): CardDatabase {
    synchronized(CardDatabase::class.java) {
        return Room.databaseBuilder(context.applicationContext,
            CardDatabase::class.java,"cards").build()
    }
}