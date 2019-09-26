package ejarosiewicz.com.apptemplate.requester.usecase

import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.apptemplate.requester.usecase.data.Header
import ejarosiewicz.com.network.RequestProvider
import ejarosiewicz.com.network.data.SomeData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDataFromWebUseCaseImpl @Inject constructor(
    private val requestProvider: RequestProvider,
    private val converter: SomeDataToDataToShowConverter
) :
    GetDataFromWebUseCase {

    override fun load(): Single<List<DataToShow>> = requestProvider.someRequest()
        .map { someDataList -> someDataList.prepareDataList() }

    private fun List<SomeData>.prepareDataList() = this
        .map { data -> converter.convert(data) }
        .sortedBy { dataToShow -> dataToShow !is Header }
}