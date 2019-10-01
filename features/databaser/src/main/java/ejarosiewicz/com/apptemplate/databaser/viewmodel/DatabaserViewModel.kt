package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ejarosiewicz.com.android.connection.NetworkConnection
import ejarosiewicz.com.android.logger.Logger
import ejarosiewicz.com.apptemplate.databaser.data.*
import ejarosiewicz.com.apptemplate.databaser.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.databaser.usecase.data.DataToShow
import ejarosiewicz.com.async.Scheduler
import javax.inject.Inject

class DatabaserViewModel @Inject constructor(
    private val getDataFromWebUseCase: GetDataFromWebUseCase,
    private val scheduler: Scheduler,
    private val logger: Logger,
    private val networkConnection: NetworkConnection
) : ViewModel() {

    private val subscriberTag = this::class.toString()

    val request = MutableLiveData<DatabaserState>()

    fun loadDataFromWeb() {
        request.value = RequestLoading
        if (networkConnection.isEnabled()) {
            makeAsynchronousRequest()
        } else {
            notifyNoNetworkConnection()
        }
    }

    private fun makeAsynchronousRequest() =
        scheduler.schedule(
            subscriber = subscriberTag,
            source = getDataFromWebUseCase.load(),
            onComplete = { data -> onLoadDataSuccess(data) },
            onError = { throwable -> onReceiveError(throwable) })

    private fun notifyNoNetworkConnection() {
        request.value = RequestNoNetwork
    }

    private fun onLoadDataSuccess(data: List<DataToShow>) {
        request.value = RequestSuccessful(data)
    }

    private fun onReceiveError(error: Throwable) {
        logger.logError(this::class.toString(), error.message.orEmpty())
        request.value = RequestFailed
    }
}