package ejarosiewicz.com.apptemplate.requester.usecase

import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.requesting.RequestProvider
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDataFromWebUseCaseImpl @Inject constructor(private val requestProvider: RequestProvider,
                                                    private val converter: SomeDataToDataToShowConverter) :
    GetDataFromWebUseCase {

    override fun load(): Single<DataToShow> = requestProvider.someRequest()
        .map { someData -> converter.convert(someData)  }
}