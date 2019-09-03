package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.TestScheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RequesterViewModelImplTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testScheduler: Scheduler = TestScheduler()

    private val mockStateObserver: Observer<String> = mock()
    private val mockGetDataFromWebUseCase: GetDataFromWebUseCase = mock {
        on { load() } doReturn Single.just(STUB_RESPONSE)
    }

    private val systemUnderTest = RequesterViewModelImpl(mockGetDataFromWebUseCase, testScheduler)

    @Before
    fun `Set up`() {
        systemUnderTest.request.observeForever(mockStateObserver)
    }

    @Test
    fun `Use get data from web use case to get data`() {
        systemUnderTest.loadDataFromWeb()

        verify(mockGetDataFromWebUseCase).load()
    }

    @Test
    fun `Fetch data from web`() {
        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(STUB_RESPONSE)
    }

    companion object {

        private const val STUB_RESPONSE = "Hello"
    }
}