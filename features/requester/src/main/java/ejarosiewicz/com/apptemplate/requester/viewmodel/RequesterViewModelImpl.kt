package ejarosiewicz.com.apptemplate.requester.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ejarosiewicz.com.apptemplate.requester.RequesterViewModel

class RequesterViewModelImpl : ViewModel(), RequesterViewModel {


    val request: LiveData<String> = MutableLiveData()

    override fun loadDataFromWeb() {

    }

}