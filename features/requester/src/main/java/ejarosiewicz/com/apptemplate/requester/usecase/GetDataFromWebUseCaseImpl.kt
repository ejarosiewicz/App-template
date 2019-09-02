package ejarosiewicz.com.apptemplate.requester.usecase

import ejarosiewicz.com.requesting.RequestProvider
import io.reactivex.Single

class GetDataFromWebUseCaseImpl(private val requestProvider: RequestProvider) : GetDataFromWebUseCase {

    override fun load(): Single<String> = requestProvider.someRequest()
}