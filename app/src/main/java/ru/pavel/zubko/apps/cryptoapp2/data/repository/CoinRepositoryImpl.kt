package ru.pavel.zubko.apps.cryptoapp2.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.delay
import ru.pavel.zubko.apps.cryptoapp2.data.database.AppDatabase
import ru.pavel.zubko.apps.cryptoapp2.data.mapper.CoinMapper
import ru.pavel.zubko.apps.cryptoapp2.data.network.ApiFactory
import ru.pavel.zubko.apps.cryptoapp2.domain.CoinInfo
import ru.pavel.zubko.apps.cryptoapp2.domain.CoinRepository
import kotlin.collections.map

class CoinRepositoryImpl(
    private val application: Application
): CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            val topCoins = apiService.getTopCoinsInfo(limit = 50)
            val fSyms = mapper.mapNamesListToString(topCoins)
            val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
            val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
            val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
            coinInfoDao.insertPriceList(dbModelList)
            delay(10000)
        }
    }
}
