package ejarosiewicz.com.apptemplate.requester.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.apptemplate.requester.usecase.data.Header
import ejarosiewicz.com.network.RequestProvider
import ejarosiewicz.com.network.data.SomeData
import io.reactivex.Single
import org.junit.Test

class GetDataFromWebUseCaseImplTest {
    private val mockDataToShow: DataToShow = mock()

    private val mockRequestProvider: RequestProvider = mock {
        on { someRequest() } doReturn Single.just(listOf(STUB_SOME_DATA))
    }
    private val mockConverter: SomeDataToDataToShowConverter = mock {
        on { convert(STUB_SOME_DATA) } doReturn mockDataToShow
        on { convert(STUB_SOME_DATA_HEADER) } doReturn STUB_HEADER
    }

    private val systemUnderTest = GetDataFromWebUseCaseImpl(mockRequestProvider, mockConverter)

    @Test
    fun `Use request provider for gathering data`() {
        systemUnderTest.load()

        verify(mockRequestProvider).someRequest()
    }

    @Test
    fun `Use converter to get data to show`() {
        systemUnderTest.load()
            .test()

        verify(mockConverter).convert(STUB_SOME_DATA)
    }

    @Test
    fun `Add header at the beginning`() {
        givenListWithLastHeader()

        systemUnderTest.load()
            .test()
            .assertOf { observer ->
                observer.assertValue { list ->
                    list[0] is Header
                }
            }
    }

    private fun givenListWithLastHeader(){
        val listWithHeaderLast = listOf(STUB_SOME_DATA, STUB_SOME_DATA, STUB_SOME_DATA_HEADER)
        whenever(mockRequestProvider.someRequest()).thenReturn(Single.just(listWithHeaderLast))
    }

    companion object {
        private val STUB_SOME_DATA = SomeData(false, "yellow", "http://hotporn.com/ass.png")
        private val STUB_SOME_DATA_HEADER = SomeData(true, "header", "http://hotporn.com/ass.png")
        private val STUB_HEADER = Header("head", "enough")
    }
}