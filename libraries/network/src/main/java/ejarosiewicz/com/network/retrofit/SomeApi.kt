package ejarosiewicz.com.network.retrofit

import ejarosiewicz.com.network.NetworkConstants
import ejarosiewicz.com.network.data.SomeData
import io.reactivex.Single
import retrofit2.http.GET

interface SomeApi {

    @GET(NetworkConstants.SOME_REQUEST)
    fun someRequest(): Single<SomeData>

    @GET(NetworkConstants.ANOTHER_REQUEST)
    fun anotherRequest(): Single<String>
}