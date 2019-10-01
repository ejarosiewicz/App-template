package ejarosiewicz.com.apptemplate.databaser.usecase.converter

import ejarosiewicz.com.apptemplate.databaser.usecase.data.DataToShow
import ejarosiewicz.com.network.data.SomeData

interface AnotherDataToDataToShowConverter {

    fun convert(someData: SomeData): DataToShow
}