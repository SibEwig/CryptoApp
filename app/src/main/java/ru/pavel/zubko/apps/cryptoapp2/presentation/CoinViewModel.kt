package ru.pavel.zubko.apps.cryptoapp2.presentation

import androidx.lifecycle.ViewModel
import ru.pavel.zubko.apps.cryptoapp2.domain.GetCoinInfoListUseCase
import ru.pavel.zubko.apps.cryptoapp2.domain.GetCoinInfoUseCase
import ru.pavel.zubko.apps.cryptoapp2.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}