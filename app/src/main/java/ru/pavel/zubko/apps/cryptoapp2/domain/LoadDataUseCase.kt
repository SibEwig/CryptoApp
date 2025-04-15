package ru.pavel.zubko.apps.cryptoapp2.domain

class LoadDataUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.loadData()
}
