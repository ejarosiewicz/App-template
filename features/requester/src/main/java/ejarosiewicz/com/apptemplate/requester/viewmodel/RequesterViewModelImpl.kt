package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ejarosiewicz.com.apptemplate.requester.RequesterViewModel
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.async.Scheduler

class RequesterViewModelImpl(
    private val getDataFromWebUseCase: GetDataFromWebUseCase,
    private val scheduler: Scheduler
) : ViewModel(), RequesterViewModel {

    private val subscriberTag = this::class.toString()

    val request = MutableLiveData<String>()

    override fun loadDataFromWeb() {
        scheduler.schedule(
            subscriber = subscriberTag,
            source = getDataFromWebUseCase.load(),
            onComplete = { data -> onLoadDataSuccess(data) },
            onError = { throwable -> onReceiveError(throwable) })
    }

    private fun onLoadDataSuccess(data: String) {
        request.value = data
    }

    private fun onReceiveError(error: Throwable?) {

    }
}