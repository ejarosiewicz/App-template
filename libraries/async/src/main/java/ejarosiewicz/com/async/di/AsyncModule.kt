package ejarosiewicz.com.async.di

import dagger.Binds
import dagger.Module
import ejarosiewicz.com.async.AndroidScheduler
import io.reactivex.Scheduler

@Module
abstract class AsyncModule {

    @Binds
    abstract fun bindsScheduler(scheduler: AndroidScheduler): Scheduler

}