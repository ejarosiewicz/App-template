package ejarosiewicz.com.apptemplate.requester.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.idling.CountingIdlingResource
import dagger.android.AndroidInjection
import ejarosiewicz.com.apptemplate.requester.data.RequestFailed
import ejarosiewicz.com.apptemplate.requester.data.RequestNoNetwork
import ejarosiewicz.com.apptemplate.requester.data.RequestSuccessful
import ejarosiewicz.com.apptemplate.requester.data.RequesterState
import ejarosiewicz.com.apptemplate.requester.viewmodel.RequesterViewModelImpl
import ejarosiewicz.com.requester.R
import kotlinx.android.synthetic.main.activity_requester.*
import javax.inject.Inject


class RequesterActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    val idlingResource = CountingIdlingResource(this::class.toString())

    private lateinit var viewModel: RequesterViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_requester)
        setupViewModel()
        textView.setOnClickListener { loadDataFromWeb() }
    }

    override fun onDestroy() {
        viewModel.request.removeObservers(this)
        super.onDestroy()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModeFactory)
            .get(RequesterViewModelImpl::class.java)
        viewModel.request.observe(this, Observer { data -> onDataReceived(data) })
    }

    private fun loadDataFromWeb() {
        idlingResource.increment()
        viewModel.loadDataFromWeb()
    }

    private fun onDataReceived(requesterState: RequesterState) {
        when (requesterState) {
            is RequestSuccessful -> onRequestSuccessful(requesterState)
            is RequestFailed -> onRequestFailed()
            is RequestNoNetwork -> notifyNoNetworkConnection()
        }
        idlingResource.decrement()
    }

    private fun notifyNoNetworkConnection() {
        textView.setText(R.string.no_network_connection)
    }

    private fun onRequestSuccessful(requestSuccessful: RequestSuccessful) {
        //textView.text = requestSuccessful.data
    }

    private fun onRequestFailed() {
        textView.setText(R.string.network_error)
    }
}