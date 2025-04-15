package ru.pavel.zubko.apps.cryptoapp2.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ru.pavel.zubko.apps.cryptoapp2.data.database.AppDatabase
import ru.pavel.zubko.apps.cryptoapp2.data.mapper.CoinMapper
import ru.pavel.zubko.apps.cryptoapp2.domain.CoinInfo
import ru.pavel.zubko.apps.cryptoapp2.domain.CoinRepository
import ru.pavel.zubko.apps.cryptoapp2.workers.RefreshDataWorker

class CoinRepositoryImpl(
    private val application: Application,
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}
