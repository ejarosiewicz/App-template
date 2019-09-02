package ejarosiewicz.com.apptemplate.requester.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ejarosiewicz.com.requester.R

class RequesterActivity: AppCompatActivity() {
//
//    @Inject
//    lateinit var viewModeFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
       //
        super.onCreate(savedInstanceState)
        //AndroidInjection.inject(this)
        setContentView(R.layout.activity_requester)
       // viewModel = ViewModelProviders.of(this, viewModeFactory).get(RequesterViewModelImpl::class.java)
    }
}