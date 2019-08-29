package ejarosiewicz.com.requesting

import io.reactivex.Single

interface RequestProvider {

    fun someRequest(): Single<String>

    fun anotherRequest(): Single<String>
}