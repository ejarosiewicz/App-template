package ejarosiewicz.com.apptemplate.requester.usecase.converter

import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.requesting.data.SomeData

class SomeDataToDataToShowConverterImpl: SomeDataToDataToShowConverter {

    override fun convert(someData: SomeData)= DataToShow(
        text = someData.hello
    )
}