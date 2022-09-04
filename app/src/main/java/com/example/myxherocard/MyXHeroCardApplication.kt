package com.example.myxherocard

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.*
import com.example.myxherocard.injection.mainModule
import com.example.myxherocard.worker.DataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

open class MyXHeroCardApplication: Application() {
    private companion object {
        private val TAG = MyXHeroCardApplication::class.simpleName
    }

    private val appScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyXHeroCardApplication)
            modules(mainModule)
        }

        appScope.launch {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(false)
                .setRequiresCharging(false)
                .build()

            val workRequest = PeriodicWorkRequest.Builder(DataWorker::class.java,1, TimeUnit.DAYS)
            workRequest.setConstraints(constraints)
            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(DataWorker.WORK_ID,
                ExistingPeriodicWorkPolicy.KEEP, workRequest.build())

            Log.d(TAG, "Data parser worker enqueued")
        }
    }
}