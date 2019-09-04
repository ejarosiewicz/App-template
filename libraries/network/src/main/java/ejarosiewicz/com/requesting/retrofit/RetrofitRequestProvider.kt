package ejarosiewicz.com.requesting.retrofit

import ejarosiewicz.com.requesting.RequestProvider
import javax.inject.Inject

class RetrofitRequestProvider @Inject constructor(private val someApi: SomeApi) : RequestProvider {

    override fun someRequest() = someApi.someRequest()

    override fun anotherRequest() = someApi.anotherRequest()

}