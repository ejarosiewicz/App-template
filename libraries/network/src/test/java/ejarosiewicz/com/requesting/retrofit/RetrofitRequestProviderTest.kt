package ejarosiewicz.com.requesting.retrofit

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

class RetrofitRequestProviderTest {

    private val mockSomeApi: SomeApi = mock()

    private val systemUnderTest = RetrofitRequestProvider(mockSomeApi)

    @Test
    fun `Call some request from some api`() {
        systemUnderTest.someRequest()

        verify(mockSomeApi).someRequest()
    }

    @Test
    fun `Call another request from some api`() {
        systemUnderTest.anotherRequest()

        verify(mockSomeApi).anotherRequest()
    }
}