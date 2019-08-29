package ejarosiewicz.com.requesting.retrofit

import ejarosiewicz.com.requesting.NetworkConstants
import io.reactivex.Single
import retrofit2.http.GET

interface SomeApi {

    @GET(NetworkConstants.SOME_REQUEST)
    fun someRequest(): Single<String>

    @GET(NetworkConstants.ANOTHER_REQUEST)
    fun anotherRequest(): Single<String>
}