package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import org.junit.Before
import org.junit.Test

class RequesterViewModelImplTest {

    private val mockStateObserver: Observer<String> = mock()
    private val mockGetDataFromWebUseCase: GetDataFromWebUseCase = mock()

    private val systemUnderTest = RequesterViewModelImpl(mockGetDataFromWebUseCase)

    @Before
    fun `Set up`(){
        systemUnderTest.request.observeForever(mockStateObserver)
    }

    @Test
    fun `Use get data from web use case to get data`() {
        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(STUB_RESPONSE)
    }
    @Test
    fun `Fetch data from web`() {
        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(STUB_RESPONSE)
    }

    companion object{

        private const val STUB_RESPONSE = "Hello"
    }
}