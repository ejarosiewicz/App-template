package ejarosiewicz.com.apptemplate.requester.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.requesting.RequestProvider
import org.junit.Test

class GetDataFromWebUseCaseImplTest {

    private val mockRequestProvider: RequestProvider = mock()

    private val systemUnderTest = GetDataFromWebUseCaseImpl(mockRequestProvider)

    @Test
    fun `Use request provider for gathering data`() {
        systemUnderTest.load()

        verify(mockRequestProvider).someRequest()
    }
}