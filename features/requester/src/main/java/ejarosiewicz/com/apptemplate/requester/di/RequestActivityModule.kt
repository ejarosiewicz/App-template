package ejarosiewicz.com.apptemplate.requester.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ejarosiewicz.com.android.di.AndroidElementsModule
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import ejarosiewicz.com.apptemplate.requester.viewmodel.RequesterViewModelImpl

@Module(includes = [AndroidElementsModule::class])
abstract class RequestActivityModule {

    @Binds
    @IntoMap
    @ClassKey(RequesterViewModelImpl::class)
    abstract fun bindsRequesterViewModel(requesterViewModel: RequesterViewModelImpl): ViewModel

    @ContributesAndroidInjector
    abstract fun bindsRequesterActivity(): RequesterActivity
}