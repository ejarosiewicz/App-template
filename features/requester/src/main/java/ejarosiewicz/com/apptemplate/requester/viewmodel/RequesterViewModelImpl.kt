package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ejarosiewicz.com.android.logger.Logger
import ejarosiewicz.com.apptemplate.requester.RequesterViewModel
import ejarosiewicz.com.apptemplate.requester.data.RequestFailed
import ejarosiewicz.com.apptemplate.requester.data.RequestSuccessful
import ejarosiewicz.com.apptemplate.requester.data.RequesterState
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import ejarosiewicz.com.async.Scheduler
import javax.inject.Inject

class RequesterViewModelImpl @Inject constructor(
    private val getDataFromWebUseCase: GetDataFromWebUseCase,
    private val scheduler: Scheduler,
    private val logger: Logger
) : ViewModel(), RequesterViewModel {

    private val subscriberTag = this::class.toString()

    val request = MutableLiveData<RequesterState>()

    override fun loadDataFromWeb() {
        scheduler.schedule(
            subscriber = subscriberTag,
            source = getDataFromWebUseCase.load(),
            onComplete = { data -> onLoadDataSuccess(data) },
            onError = { throwable -> onReceiveError(throwable) })
    }

    private fun onLoadDataSuccess(data: DataToShow) {
        request.value = RequestSuccessful(data.text)
    }

    private fun onReceiveError(error: Throwable) {
        logger.logError(this::class.toString(), error.message.orEmpty())
        request.value = RequestFailed
    }
}