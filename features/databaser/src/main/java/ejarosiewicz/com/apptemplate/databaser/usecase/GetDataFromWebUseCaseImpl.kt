package ejarosiewicz.com.apptemplate.databaser.usecase

import ejarosiewicz.com.apptemplate.databaser.usecase.converter.AnotherDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.databaser.usecase.data.DataToShow
import ejarosiewicz.com.apptemplate.requester.usecase.data.Header
import ejarosiewicz.com.network.RequestProvider
import ejarosiewicz.com.network.data.SomeData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDataFromWebUseCaseImpl @Inject constructor(
    private val requestProvider: RequestProvider,
    private val converter: AnotherDataToDataToShowConverter
) :
    GetDataFromWebUseCase {

    override fun load(): Single<List<DataToShow>> = requestProvider.someRequest()
        .map { someDataList -> someDataList.prepareDataList() }

    private fun List<SomeData>.prepareDataList() = this
        .map { data -> converter.convert(data) }
        .sortedBy { dataToShow -> dataToShow !is Header }
}