package ru.pavel.zubko.apps.cryptoapp2.domain

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}
