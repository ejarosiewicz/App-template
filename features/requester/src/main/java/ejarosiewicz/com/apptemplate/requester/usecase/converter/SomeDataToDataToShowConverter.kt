package ejarosiewicz.com.apptemplate.requester.usecase.converter

import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.requesting.data.SomeData

interface SomeDataToDataToShowConverter {

    fun convert(someData: SomeData): DataToShow
}