package ru.pavel.zubko.apps.cryptoapp2.domain

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}
