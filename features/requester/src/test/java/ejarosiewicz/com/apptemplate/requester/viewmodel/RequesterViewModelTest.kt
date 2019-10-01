package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.nhaarman.mockitokotlin2.*
import ejarosiewicz.com.android.connection.NetworkConnection
import ejarosiewicz.com.android.logger.Logger
import ejarosiewicz.com.apptemplate.requester.data.*
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.async.Scheduler
import ejarosiewicz.com.async.TestScheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RequesterViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testScheduler: Scheduler = TestScheduler()

    private val mockDataToShow: DataToShow = mock()
    private val stubResponse = listOf(mockDataToShow)
    private val mockStateObserver: Observer<RequesterState> = mock()
    private val mockGetDataFromWebUseCase: GetDataFromWebUseCase = mock()
    private val mockLogger: Logger = mock()
    private val mockNetworkConnection: NetworkConnection = mock()


    private val systemUnderTest = RequesterViewModel(mockGetDataFromWebUseCase,
        testScheduler, mockLogger, mockNetworkConnection)

    @Before
    fun `Set up`() {
        systemUnderTest.request.observeForever(mockStateObserver)
    }

    @Test
    fun `Notify loading state at the beginning of loading data from web`() {
        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(RequestLoading)
    }
    @Test
    fun `Fetch no network state`() {
        givenNoNetwork()

        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(RequestNoNetwork)
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
        val stateCaptor = argumentCaptor<RequesterState>()

        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver, times(2)).onChanged(stateCaptor.capture())
        stateCaptor.apply {
            assertThat(secondValue).isInstanceOf(RequestSuccessful::class)
            val gatheredData = (secondValue as RequestSuccessful).data
            assertThat(gatheredData).isEqualTo(stubResponse)
        }
    }

    @Test
    fun `Log error`() {
        givenErrorFromWeb()

        systemUnderTest.loadDataFromWeb()

        verify(mockLogger).logError(RequesterViewModel::class.toString(), EXCEPTION_MESSAGE)
    }

    @Test
    fun `Fetch data by error state`() {
        givenErrorFromWeb()

        systemUnderTest.loadDataFromWeb()

        verify(mockStateObserver).onChanged(RequestFailed)
    }

    private fun givenNoNetwork(){
        whenever(mockNetworkConnection.isEnabled()).thenReturn(false)
    }

    private fun givenErrorFromWeb() {
        whenever(mockNetworkConnection.isEnabled()).thenReturn(true)
        whenever(mockGetDataFromWebUseCase.load()).thenReturn(Single.error(EXCEPTION))
    }

    private fun givenDataFromWeb() {
        whenever(mockNetworkConnection.isEnabled()).thenReturn(true)
        whenever(mockGetDataFromWebUseCase.load()).thenReturn(Single.just(stubResponse))
    }

    companion object {
        private const val EXCEPTION_MESSAGE = "You smell"

        private val EXCEPTION = IllegalAccessException(EXCEPTION_MESSAGE)
    }
}