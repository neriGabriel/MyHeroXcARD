package com.example.myxherocard

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.*
import com.example.myxherocard.injection.mainModule
import com.example.myxherocard.viewmodel.CardsViewModel
import com.example.myxherocard.worker.DataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

open class MyXHeroCardApplication: Application() {
    private companion object {
        private val TAG = MyXHeroCardApplication::class.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyXHeroCardApplication)
            modules(mainModule)
        }

        CoroutineScope(Dispatchers.Default).launch {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        setRequiresDeviceIdle(true)
                }.build()

            val workRequest = PeriodicWorkRequestBuilder<DataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(DataWorker.WORK_ID,
                ExistingPeriodicWorkPolicy.KEEP, workRequest)

            Log.d(TAG, "Data parser worker enqueded")
        }
    }
}