package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ejarosiewicz.com.apptemplate.requester.RequesterViewModel
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RequesterViewModelImpl(private val getDataFromWebUseCase: GetDataFromWebUseCase) : ViewModel(), RequesterViewModel {


    val request = MutableLiveData<String>()

    override fun loadDataFromWeb() {
        getDataFromWebUseCase.load()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe ({ data -> onLoadDataSuccess(data)},{error-> onReceiveError(error) })
    }

    private fun onLoadDataSuccess(data: String) {
        request.value = data
    }

    private fun onReceiveError(error: Throwable?) {

    }

}