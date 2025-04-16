package ru.pavel.zubko.apps.cryptoapp2.di

import androidx.work.ListenableWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.pavel.zubko.apps.cryptoapp2.workers.ChildWorkerFactory
import ru.pavel.zubko.apps.cryptoapp2.workers.RefreshDataWorker

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}