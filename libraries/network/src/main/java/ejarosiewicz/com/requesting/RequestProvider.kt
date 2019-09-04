package ejarosiewicz.com.requesting

import ejarosiewicz.com.requesting.data.SomeData
import io.reactivex.Single

interface RequestProvider {

    fun someRequest(): Single<SomeData>

    fun anotherRequest(): Single<String>
}