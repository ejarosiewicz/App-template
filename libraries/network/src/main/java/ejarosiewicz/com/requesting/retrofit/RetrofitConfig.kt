package ejarosiewicz.com.requesting.retrofit

import ejarosiewicz.com.requesting.NetworkConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    fun createApi(): SomeApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SomeApi::class.java)
    }
}