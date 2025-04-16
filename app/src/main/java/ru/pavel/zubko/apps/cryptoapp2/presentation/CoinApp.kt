package ru.pavel.zubko.apps.cryptoapp2.presentation

import android.app.Application
import ru.pavel.zubko.apps.cryptoapp2.di.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}