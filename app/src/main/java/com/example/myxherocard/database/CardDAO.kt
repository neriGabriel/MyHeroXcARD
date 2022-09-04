package com.example.myxherocard.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CardDAO {

    @Query("SELECT * FROM cards")
    fun getCards(): LiveData<List<DatabaseCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cards: List<DatabaseCard>): List<Long>

    @Update
    fun update(card: DatabaseCard)
}