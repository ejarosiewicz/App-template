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

        assertThat(expectedDataToShow.isHeader).isEqualTo(STUB_SOME_DATA.isHeader)
        assertThat(expectedDataToShow.text).isEqualTo(STUB_SOME_DATA.text)
        assertThat(expectedDataToShow.image).isEqualTo(STUB_SOME_DATA.image)
    }

    companion object{
        private const val STUB_TEXT = "You moron"
        private const val STUB_IMAGE = "http://goatse.cx/hello.jpg"
        private const val STUB_IS_HEADER = false
        private val STUB_SOME_DATA = SomeData(STUB_IS_HEADER,STUB_TEXT, STUB_IMAGE)
    }
}