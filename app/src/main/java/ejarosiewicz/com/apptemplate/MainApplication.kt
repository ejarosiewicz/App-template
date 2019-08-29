package ejarosiewicz.com.apptemplate

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ejarosiewicz.com.apptemplate.di.DaggerMainApplicationComponent
import javax.inject.Inject


class MainApplication : Application(), HasAndroidInjector{

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerMainApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> =
        activityDispatchingAndroidInjector

}