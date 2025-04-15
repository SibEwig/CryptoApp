package ru.pavel.zubko.apps.cryptoapp2.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.pavel.zubko.apps.cryptoapp2.data.repository.CoinRepositoryImpl
import ru.pavel.zubko.apps.cryptoapp2.domain.GetCoinInfoListUseCase
import ru.pavel.zubko.apps.cryptoapp2.domain.GetCoinInfoUseCase
import ru.pavel.zubko.apps.cryptoapp2.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}