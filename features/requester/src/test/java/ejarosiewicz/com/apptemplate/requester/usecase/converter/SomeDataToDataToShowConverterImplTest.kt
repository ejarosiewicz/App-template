package ejarosiewicz.com.apptemplate.requester.usecase.converter

import assertk.assertThat
import assertk.assertions.isEqualTo
import ejarosiewicz.com.network.data.SomeData
import org.junit.Test

class SomeDataToDataToShowConverterImplTest{

    private val systemUnderTest = SomeDataToDataToShowConverterImpl()

    @Test
    fun `Convert some data to data to show`(){
        val expectedDataToShow = systemUnderTest.convert(STUB_SOME_DATA)

        assertThat(expectedDataToShow.text).isEqualTo(STUB_SOME_DATA.hello)
    }

    companion object{
        private const val STUB_TEXT = "You moron"
        private val STUB_SOME_DATA = SomeData(STUB_TEXT)
    }
}