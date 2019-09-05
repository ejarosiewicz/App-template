package ejarosiewicz.com.apptemplate.requester.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.idling.CountingIdlingResource
import dagger.android.AndroidInjection
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

    private fun loadDataFromWeb(){
        idlingResource.increment()
        viewModel.loadDataFromWeb()
    }

    private fun onDataReceived(data: String) {
        textView.text = data
        idlingResource.decrement()
    }
}