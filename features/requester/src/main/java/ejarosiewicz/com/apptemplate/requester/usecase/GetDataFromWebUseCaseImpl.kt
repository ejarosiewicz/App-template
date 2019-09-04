package ejarosiewicz.com.apptemplate.requester.usecase

import ejarosiewicz.com.requesting.RequestProvider
import io.reactivex.Single
import javax.inject.Inject

class GetDataFromWebUseCaseImpl @Inject constructor(private val requestProvider: RequestProvider) :
    GetDataFromWebUseCase {

    override fun load(): Single<String> = requestProvider.someRequest()
}