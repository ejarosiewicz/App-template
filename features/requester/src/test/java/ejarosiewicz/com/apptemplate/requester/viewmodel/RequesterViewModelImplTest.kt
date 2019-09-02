package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

class RequesterViewModelImplTest {

    private val mockStateObserver: Observer<String> = mock()

    private val systemUnderTest = RequesterViewModelImpl()

    @Before
    fun `Set up`(){
        systemUnderTest.request.observeForever(mockStateObserver)
    }

    @Test
    fun loadDataFromWeb() {
        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(STUB_RESPONSE)
    }

    companion object{

        private const val STUB_RESPONSE = "Hello"
    }
}