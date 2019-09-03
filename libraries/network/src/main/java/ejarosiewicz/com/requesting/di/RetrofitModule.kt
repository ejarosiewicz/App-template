package ejarosiewicz.com.requesting.di

import dagger.Module
import dagger.Provides
import ejarosiewicz.com.requesting.retrofit.RetrofitConfig
import ejarosiewicz.com.requesting.retrofit.SomeApi

@Module
class RetrofitModule {

    @Provides
    fun provideSomeApi():SomeApi = RetrofitConfig.createApi()

}