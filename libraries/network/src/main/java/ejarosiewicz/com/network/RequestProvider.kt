package ejarosiewicz.com.network

import ejarosiewicz.com.network.data.SomeData
import io.reactivex.Single

interface RequestProvider {

    fun someRequest(): Single<List<SomeData>>

    fun anotherRequest(): Single<String>
}