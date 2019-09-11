package ejarosiewicz.com.apptemplate.requester.usecase.converter

import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.network.data.SomeData

interface SomeDataToDataToShowConverter {

    fun convert(someData: SomeData): DataToShow
}