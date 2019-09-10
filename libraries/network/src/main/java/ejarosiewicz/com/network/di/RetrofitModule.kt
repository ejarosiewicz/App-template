package ejarosiewicz.com.network.di

import dagger.Module
import dagger.Provides
import ejarosiewicz.com.network.retrofit.RetrofitConfig
import ejarosiewicz.com.network.retrofit.SomeApi

@Module
class RetrofitModule {

    @Provides
    fun provideSomeApi():SomeApi = RetrofitConfig.createApi()

}