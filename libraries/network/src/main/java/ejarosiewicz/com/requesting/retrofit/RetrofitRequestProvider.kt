package ejarosiewicz.com.requesting.retrofit

import ejarosiewicz.com.requesting.RequestProvider

class RetrofitRequestProvider(val someApi: SomeApi) : RequestProvider {

    override fun someRequest() = someApi.someRequest()

    override fun anotherRequest() = someApi.anotherRequest()

}