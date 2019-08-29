package ejarosiewicz.com.apptemplate.requester.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ejarosiewicz.com.android.di.AndroidElementsModule
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity

@Module(includes = [AndroidElementsModule::class])
abstract class RequestActivityModule {

//    @Binds
//    @IntoMap
//    @ClassKey(RequesterViewModel::class)
//    abstract fun bindsViewModel(requesterViewModel: RequesterViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun bindsRequesterActivity(): RequesterActivity
}