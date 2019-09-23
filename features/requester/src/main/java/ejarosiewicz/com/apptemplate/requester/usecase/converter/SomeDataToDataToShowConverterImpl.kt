package ejarosiewicz.com.apptemplate.requester.usecase.converter

import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.network.data.SomeData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SomeDataToDataToShowConverterImpl @Inject constructor(): SomeDataToDataToShowConverter {

    override fun convert(someData: SomeData)= DataToShow(
        isHeader = someData.isHeader,
        text = someData.text,
        image = someData.image
    )
}