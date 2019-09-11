package ejarosiewicz.com.network.retrofit

import ejarosiewicz.com.network.NetworkConstants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    fun createApi(): SomeApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.MAIN_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SomeApi::class.java)
    }
}