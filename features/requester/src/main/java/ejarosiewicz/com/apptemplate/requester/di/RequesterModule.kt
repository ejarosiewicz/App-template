package ejarosiewicz.com.apptemplate.requester.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import ejarosiewicz.com.apptemplate.requester.viewmodel.RequesterViewModelImpl

@Module
abstract class RequesterModule {

    @Binds
    @IntoMap
    @ClassKey(RequesterViewModelImpl::class)
    abstract fun bindsRequesterViewModel(requesterViewModel: RequesterViewModelImpl): ViewModel

    @ContributesAndroidInjector
    abstract fun bindsRequesterActivity(): RequesterActivity
}