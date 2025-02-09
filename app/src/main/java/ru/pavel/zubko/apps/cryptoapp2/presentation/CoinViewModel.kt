package ru.pavel.zubko.apps.cryptoapp2.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pavel.zubko.apps.cryptoapp2.data.repository.CoinRepositoryImpl
import ru.pavel.zubko.apps.cryptoapp2.domain.GetCoinInfoListUseCase
import ru.pavel.zubko.apps.cryptoapp2.domain.GetCoinInfoUseCase
import ru.pavel.zubko.apps.cryptoapp2.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListtUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListtUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}