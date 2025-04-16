package ru.pavel.zubko.apps.cryptoapp2.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.pavel.zubko.apps.cryptoapp2.presentation.CoinApp
import ru.pavel.zubko.apps.cryptoapp2.presentation.CoinDetailFragment
import ru.pavel.zubko.apps.cryptoapp2.presentation.CoinPriceListActivity

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}