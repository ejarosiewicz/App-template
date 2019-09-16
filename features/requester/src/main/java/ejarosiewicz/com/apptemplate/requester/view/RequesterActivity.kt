package ejarosiewicz.com.apptemplate.requester.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.idling.CountingIdlingResource
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import ejarosiewicz.com.apptemplate.requester.data.RequestFailed
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

    private fun onDataReceived(requesterState: RequesterState) =
        when (requesterState) {
            is RequestSuccessful -> onRequestSuccessful(requesterState)
            is RequestFailed -> onRequestFailed()
        }

    private fun onRequestSuccessful(requestSuccessful: RequestSuccessful) {
        textView.text = requestSuccessful.data
        idlingResource.decrement()
    }

    private fun onRequestFailed() {
        Snackbar.make(container, R.string.network_error, Snackbar.LENGTH_SHORT).show()
    }
}