package ejarosiewicz.com.apptemplate.databaser.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ejarosiewicz.com.apptemplate.requester.viewmodel.DatabaserViewModel

@Module(includes = [UseCaseModule::class])
abstract class DatabaserModule {

    @Binds
    @IntoMap
    @ViewModelKey(DatabaserViewModel::class)
    abstract fun bindsDatabaserViewModel(requesterViewModel: DatabaserViewModel): ViewModel
//
//    @ContributesAndroidInjector
//    abstract fun bindsRequesterActivity(): RequesterActivity

}