package ejarosiewicz.com.apptemplate.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ejarosiewicz.com.apptemplate.MainApplication

@Component(
    modules = [AndroidInjectionModule::class,
        FeaturesModule::class,
        LibrariesModule::class]
)
interface MainApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): MainApplicationComponent
    }


    fun inject(application: MainApplication)
}