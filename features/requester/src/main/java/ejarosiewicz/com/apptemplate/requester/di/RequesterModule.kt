package ejarosiewicz.com.apptemplate.requester.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import ejarosiewicz.com.apptemplate.requester.viewmodel.RequesterViewModel

@Module(includes = [UseCaseModule::class])
abstract class RequesterModule {

    @Binds
    @IntoMap
    @ViewModelKey(RequesterViewModel::class)
    abstract fun bindsRequesterViewModel(requesterViewModel: RequesterViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun bindsRequesterActivity(): RequesterActivity

}