package ejarosiewicz.com.network.di

import dagger.Binds
import dagger.Module
import ejarosiewicz.com.network.RequestProvider
import ejarosiewicz.com.network.retrofit.RetrofitRequestProvider

@Module(includes = [RetrofitModule::class])
abstract class NetworkModule {

    @Binds
    abstract fun bindsRequestProvider(requestProvider: RetrofitRequestProvider): RequestProvider
}