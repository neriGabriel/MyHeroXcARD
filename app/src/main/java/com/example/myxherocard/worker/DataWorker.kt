package com.example.myxherocard.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.myxherocard.api.retrofit.CardRetrofit.getCardApi
import com.example.myxherocard.database.getCardDatabase
import com.example.myxherocard.repository.CardsRepository
import java.lang.Exception

class DataWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params ) {

    companion object {
        const val WORK_ID = "DatabaseDataRefreshWorker"
        private val TAG = DataWorker::class.simpleName
    }

    override suspend fun doWork(): Result {
        val database = getCardDatabase(applicationContext)
        val service = getCardApi()
        val repository = CardsRepository(database, service)

        return try {
            repository.getAllCards()
            Log.d(TAG, "Parsing data from remote to local was successfully executed")
            Result.success()
        } catch(error: Exception) {
            Log.w(TAG, "Error while parsing data from romete to local ${error.message}")
            Result.retry()
        }
    }
}