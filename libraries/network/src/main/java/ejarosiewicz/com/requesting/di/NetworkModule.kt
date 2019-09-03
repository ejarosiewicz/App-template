package ejarosiewicz.com.requesting.di

import dagger.Binds
import dagger.Module
import ejarosiewicz.com.requesting.RequestProvider
import ejarosiewicz.com.requesting.retrofit.RetrofitRequestProvider

@Module(includes = [RetrofitModule::class])
abstract class NetworkModule {

    @Binds
    abstract fun bindsRequestProvider(requestProvider: RetrofitRequestProvider): RequestProvider
}