package ru.pavel.zubko.apps.cryptoapp2.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.pavel.zubko.apps.cryptoapp2.presentation.CoinViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindViewModel(impl: CoinViewModel): ViewModel
}