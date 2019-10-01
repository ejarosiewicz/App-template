package ejarosiewicz.com.apptemplate.requester.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.idling.CountingIdlingResource
import dagger.android.AndroidInjection
import ejarosiewicz.com.android.imageloader.ImageLoader
import ejarosiewicz.com.apptemplate.requester.data.*
import ejarosiewicz.com.apptemplate.requester.view.adapter.RequestAdapter
import ejarosiewicz.com.apptemplate.requester.viewmodel.RequesterViewModel
import ejarosiewicz.com.requester.R
import kotlinx.android.synthetic.main.activity_requester.*
import javax.inject.Inject

class RequesterActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    @Inject
    lateinit var imageLoader: ImageLoader

    val idlingResource = CountingIdlingResource(this::class.toString())

    private lateinit var viewModel: RequesterViewModel
    private lateinit var requestAdapter: RequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_requester)
        setupViewModel()
        setupLayout()
        loadDataFromWeb()
    }

    private fun setupLayout() {
        recycler.layoutManager = LinearLayoutManager(this)
        requestAdapter = RequestAdapter(this, imageLoader)
        recycler.adapter = requestAdapter
    }

    override fun onDestroy() {
        viewModel.request.removeObservers(this)
        super.onDestroy()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModeFactory)
            .get(RequesterViewModel::class.java)
        viewModel.request.observe(this, Observer { data -> onDataReceived(data) })
    }

    private fun loadDataFromWeb() {
        idlingResource.increment()
        viewModel.loadDataFromWeb()
    }

    private fun onDataReceived(requesterState: RequesterState) {
        when (requesterState) {
            is RequestLoading -> showLoadingSpinner()
            is RequestNoNetwork -> notifyNoNetworkConnection()
            is RequestSuccessful -> onRequestSuccessful(requesterState)
            is RequestFailed -> onRequestFailed()
        }
        idlingResource.decrement()
    }

    private fun notifyNoNetworkConnection() {
        showErrorPrompt()
        errorPrompt.setText(R.string.no_network_connection)
    }

    private fun onRequestSuccessful(requestSuccessful: RequestSuccessful) {
        showRecycler()
        requestAdapter.items = requestSuccessful.data
        recycler.adapter = requestAdapter
        requestAdapter.notifyDataSetChanged()
    }

    private fun onRequestFailed() {
        showErrorPrompt()
        errorPrompt.setText(R.string.network_error)
    }

    private fun showRecycler(){
        loadingSpinner.visibility = View.GONE
        errorPrompt.visibility = View.GONE
        recycler.visibility = View.VISIBLE
    }

    private fun showLoadingSpinner() {
        loadingSpinner.visibility = View.VISIBLE
        errorPrompt.visibility = View.GONE
        recycler.visibility = View.GONE
    }

    private fun showErrorPrompt(){
        loadingSpinner.visibility = View.GONE
        errorPrompt.visibility = View.VISIBLE
        recycler.visibility = View.GONE
    }
}