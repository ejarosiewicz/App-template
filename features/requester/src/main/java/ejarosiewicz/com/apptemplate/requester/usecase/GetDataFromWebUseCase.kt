package ejarosiewicz.com.apptemplate.requester.usecase

import io.reactivex.Single

interface GetDataFromWebUseCase {

    fun load(): Single<String>
}