package ejarosiewicz.com.apptemplate.requester.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.network.RequestProvider
import ejarosiewicz.com.network.data.SomeData
import io.reactivex.Single
import org.junit.Test

class GetDataFromWebUseCaseImplTest {

    private val mockRequestProvider: RequestProvider = mock {
        on { someRequest() } doReturn Single.just(STUB_SOME_DATA)
    }
    private val mockConverter: SomeDataToDataToShowConverter = mock {
        on { convert(STUB_SOME_DATA) } doReturn STUB_DATA_TO_SHOW
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

    companion object {
        private val STUB_SOME_DATA = SomeData("yellow")
        private val STUB_DATA_TO_SHOW = DataToShow("wellow")
    }
}