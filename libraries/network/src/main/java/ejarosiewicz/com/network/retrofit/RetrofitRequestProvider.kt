package ejarosiewicz.com.network.retrofit

import ejarosiewicz.com.network.RequestProvider
import javax.inject.Inject

class RetrofitRequestProvider @Inject constructor(private val someApi: SomeApi) : RequestProvider {

    override fun someRequest() = someApi.someRequest()

    override fun anotherRequest() = someApi.anotherRequest()

}