package ejarosiewicz.com.apptemplate.databaser.usecase.converter

import ejarosiewicz.com.apptemplate.requester.usecase.data.Header
import ejarosiewicz.com.apptemplate.requester.usecase.data.NormalContent
import ejarosiewicz.com.apptemplate.requester.usecase.data.TextOnly
import ejarosiewicz.com.network.data.SomeData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnotherDataToDataToShowConverterImpl @Inject constructor() : AnotherDataToDataToShowConverter {

    override fun convert(someData: SomeData) =
        when {
            someData.isHeader -> createHeader(someData)
            someData.image.isEmpty() -> createTextOnly(someData)
            else -> createNormalContent(someData)
        }

    private fun createHeader(someData: SomeData) = Header(
        text = someData.text,
        image = someData.image
    )

    private fun createNormalContent(someData: SomeData) = NormalContent(
        text = someData.text,
        image = someData.image
    )

    private fun createTextOnly(someData: SomeData) = TextOnly(someData.text)

}