package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import ejarosiewicz.com.android.logger.Logger
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
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
    private val mockGetDataFromWebUseCase: GetDataFromWebUseCase = mock()
    private val mockLogger: Logger = mock()

    private val systemUnderTest = RequesterViewModelImpl(mockGetDataFromWebUseCase, testScheduler, mockLogger)

    @Before
    fun `Set up`() {
        systemUnderTest.request.observeForever(mockStateObserver)
    }

    @Test
    fun `Use get data from web use case to get data`() {
        givenDataFromWeb()

        systemUnderTest.loadDataFromWeb()

        verify(mockGetDataFromWebUseCase).load()
    }

    @Test
    fun `Fetch data from web`() {
        givenDataFromWeb()

        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(STUB_RESPONSE_TEXT)
    }

    @Test
    fun `Log error`() {
        givenErrorFromWeb()

        systemUnderTest.loadDataFromWeb()

        verify(mockLogger).logError(RequesterViewModelImpl::class.toString(), EXCEPTION_MESSAGE)
    }

    private fun givenErrorFromWeb() {
        whenever(mockGetDataFromWebUseCase.load()).thenReturn(Single.error(EXCEPTION))
    }

    private fun givenDataFromWeb() {
        whenever(mockGetDataFromWebUseCase.load()).thenReturn(Single.just(STUB_RESPONSE))
    }

    companion object {

        private const val STUB_RESPONSE_TEXT = "Hello"
        private const val EXCEPTION_MESSAGE = "You smell"

        private  val STUB_RESPONSE = DataToShow(STUB_RESPONSE_TEXT)
        private val EXCEPTION = IllegalAccessException(EXCEPTION_MESSAGE)
    }
}