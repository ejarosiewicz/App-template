package ejarosiewicz.com.apptemplate.requester.di

import dagger.Component
import ejarosiewicz.com.apptemplate.requester.view.RequesterActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [RequesterModule::class])
interface RequesterComponent{

    @Component.Builder
    interface Builder{
        fun build(): RequesterComponent
    }

    fun inject(activity: RequesterActivity)
}