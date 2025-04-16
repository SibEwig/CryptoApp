package ru.pavel.zubko.apps.cryptoapp2.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.pavel.zubko.apps.cryptoapp2.data.database.AppDatabase
import ru.pavel.zubko.apps.cryptoapp2.data.database.CoinInfoDao
import ru.pavel.zubko.apps.cryptoapp2.data.repository.CoinRepositoryImpl
import ru.pavel.zubko.apps.cryptoapp2.domain.CoinRepository

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}