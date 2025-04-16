package ru.pavel.zubko.apps.cryptoapp2.presentation

import android.app.Application
import androidx.work.Configuration
import ru.pavel.zubko.apps.cryptoapp2.di.DaggerApplicationComponent
import ru.pavel.zubko.apps.cryptoapp2.workers.RefreshDataWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override val workManagerConfiguration: Configuration
        get() =
            Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()
}