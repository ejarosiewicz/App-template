package ejarosiewicz.com.apptemplate.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ejarosiewicz.com.apptemplate.MainApplication

@Module
class ApplicationModule{

    @Provides
    fun provideApplicationContext(application: MainApplication): Context = application
}