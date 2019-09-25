package ejarosiewicz.com.apptemplate.requester.view

import android.opengl.Visibility
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
import ejarosiewicz.com.apptemplate.requester.data.RequestFailed
import ejarosiewicz.com.apptemplate.requester.data.RequestNoNetwork
import ejarosiewicz.com.apptemplate.requester.data.RequestSuccessful
import ejarosiewicz.com.apptemplate.requester.data.RequesterState
import ejarosiewicz.com.apptemplate.requester.view.adapter.RequestAdapter
import ejarosiewicz.com.apptemplate.requester.viewmodel.RequesterViewModelImpl
import ejarosiewicz.com.requester.R
import kotlinx.android.synthetic.main.activity_requester.*
import javax.inject.Inject


class RequesterActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    @Inject
    lateinit var imageLoader: ImageLoader

    val idlingResource = CountingIdlingResource(this::class.toString())

    private lateinit var viewModel: RequesterViewModelImpl
    private lateinit var requestAdapter: RequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_requester)
        setupViewModel()
        setupLayout()

    }

    private fun setupLayout() {
        textView.visibility = View.VISIBLE
        recycler.visibility = View.GONE
        recycler.layoutManager = LinearLayoutManager(this)
        requestAdapter = RequestAdapter(this, imageLoader)
        recycler.adapter = requestAdapter
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
        textView.visibility = View.GONE
        recycler.visibility = View.VISIBLE
        requestAdapter.items = requestSuccessful.data
        recycler.adapter = requestAdapter
        requestAdapter.notifyDataSetChanged()
    }

    private fun onRequestFailed() {
        textView.visibility = View.VISIBLE
        recycler.visibility = View.GONE
        textView.setText(R.string.network_error)
    }
}