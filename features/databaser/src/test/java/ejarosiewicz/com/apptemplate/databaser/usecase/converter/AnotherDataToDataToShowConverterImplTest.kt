package ejarosiewicz.com.apptemplate.databaser.usecase.converter

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import ejarosiewicz.com.apptemplate.requester.usecase.data.Header
import ejarosiewicz.com.apptemplate.requester.usecase.data.NormalContent
import ejarosiewicz.com.apptemplate.requester.usecase.data.TextOnly
import ejarosiewicz.com.network.data.SomeData
import org.junit.Test

class AnotherDataToDataToShowConverterImplTest{

    private val systemUnderTest = AnotherDataToDataToShowConverterImpl()

    @Test
    fun `Convert some data with header flag to header object`(){
        val givenData = generateSomeData(true, STUB_IMAGE)

        val expectedDataToShow = systemUnderTest.convert(givenData)

        assertThat(expectedDataToShow).isInstanceOf(Header::class)
        expectedDataToShow as Header
        assertThat(expectedDataToShow.text).isEqualTo(STUB_TEXT)
        assertThat(expectedDataToShow.image).isEqualTo(STUB_IMAGE)
    }

    @Test
    fun `Convert some data with empty image to text only object`(){
        val givenData = generateSomeData(false)

        val expectedDataToShow = systemUnderTest.convert(givenData)

        assertThat(expectedDataToShow).isInstanceOf(TextOnly::class)
        expectedDataToShow as TextOnly
        assertThat(expectedDataToShow.text).isEqualTo(STUB_SOME_DATA.text)
    }

    @Test
    fun `Convert some data with text and image to basic data object`(){
        val givenData = generateSomeData(false, STUB_IMAGE)

        val expectedDataToShow = systemUnderTest.convert(givenData)

        assertThat(expectedDataToShow).isInstanceOf(NormalContent::class)
        expectedDataToShow as NormalContent
        assertThat(expectedDataToShow.text).isEqualTo(STUB_TEXT)
        assertThat(expectedDataToShow.image).isEqualTo(STUB_IMAGE)
    }

    private fun generateSomeData(isHeader: Boolean = false, image: String = "") =
        SomeData(isHeader, STUB_TEXT, image)

    companion object{
        private const val STUB_TEXT = "You moron"
        private const val STUB_IMAGE = "http://goatse.cx/hello.jpg"
        private const val STUB_IS_HEADER = false
        private val STUB_SOME_DATA = SomeData(STUB_IS_HEADER, STUB_TEXT, STUB_IMAGE)
    }
}