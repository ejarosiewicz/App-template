package ejarosiewicz.com.apptemplate.databaser.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.idling.CountingIdlingResource
import dagger.android.AndroidInjection
import ejarosiewicz.com.android.imageloader.ImageLoader
import ejarosiewicz.com.apptemplate.databaser.data.*
import ejarosiewicz.com.apptemplate.databaser.view.adapter.DatabaserAdapter
import ejarosiewicz.com.apptemplate.requester.viewmodel.DatabaserViewModel
import ejarosiewicz.com.requester.R
import javax.inject.Inject

import kotlinx.android.synthetic.main.fragment_databaser.*

class DatabaserFragment : AppCompatActivity() {

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory
    @Inject
    lateinit var imageLoader: ImageLoader

    val idlingResource = CountingIdlingResource(this::class.toString())

    private lateinit var viewModel: DatabaserViewModel
    private lateinit var requestAdapter: DatabaserAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.fragment_databaser)
        setupViewModel()
        setupRecycler()
        setupSwipeToRefresh()
        loadDataFromWeb()
    }

    override fun onDestroy() {
        viewModel.request.removeObservers(this)
        super.onDestroy()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModeFactory)
            .get(DatabaserViewModel::class.java)
        viewModel.request.observe(this, Observer { data -> onDataReceived(data) })
    }

    private fun setupRecycler() {
        recycler.layoutManager = LinearLayoutManager(this)
        requestAdapter = DatabaserAdapter(this, imageLoader)
        recycler.adapter = requestAdapter
    }

    private fun setupSwipeToRefresh() {
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener { loadDataFromWeb() }
        swipeRefresh.isRefreshing = false
    }

    private fun loadDataFromWeb() {
        idlingResource.increment()
        viewModel.loadDataFromWeb()
    }

    private fun onDataReceived(requesterState: DatabaserState) {
        when (requesterState) {
            is RequestLoading -> hideErrorPrompt()
            is RequestNoNetwork -> notifyNoNetworkConnection()
            is RequestSuccessful -> onRequestSuccessful(requesterState)
            is RequestFailed -> onRequestFailed()
        }
    }

    private fun notifyNoNetworkConnection() {
        disableLoading()
        showErrorPrompt()
        errorPrompt.setText(R.string.no_network_connection)
        idlingResource.decrement()
    }

    private fun onRequestSuccessful(requestSuccessful: RequestSuccessful) {
        disableLoading()
        requestAdapter.items = requestSuccessful.data
        requestAdapter.notifyDataSetChanged()
        idlingResource.decrement()
    }

    private fun onRequestFailed() {
        disableLoading()
        showErrorPrompt()
        errorPrompt.setText(R.string.network_error)
        idlingResource.decrement()
    }

    private fun showErrorPrompt(){
        errorPrompt.visibility = View.VISIBLE
    }

    private fun hideErrorPrompt() {
        errorPrompt.visibility = View.GONE
    }

    private fun disableLoading(){
        swipeRefresh.isRefreshing = false
        loadingSpinner.visibility = View.GONE
    }
}