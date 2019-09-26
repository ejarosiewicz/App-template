package ejarosiewicz.com.apptemplate.requester.test.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import ejarosiewicz.com.apptemplate.di.ApplicationModule
import ejarosiewicz.com.apptemplate.di.FeaturesModule
import ejarosiewicz.com.apptemplate.di.LibrariesModule
import ejarosiewicz.com.apptemplate.di.MainApplicationComponent
import ejarosiewicz.com.apptemplate.requester.test.SimpleRequestFeatureTest
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    AndroidInjectionModule::class,
    ApplicationModule::class,
    FeaturesModule::class,
    LibrariesModule::class)
)
interface TestApplicationComponent : MainApplicationComponent {
    fun inject(test: SimpleRequestFeatureTest)
}