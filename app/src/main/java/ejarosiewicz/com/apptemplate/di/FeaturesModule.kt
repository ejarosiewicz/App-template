package ejarosiewicz.com.apptemplate.di

import dagger.Module
import ejarosiewicz.com.apptemplate.requester.di.RequesterModule

@Module(includes = [RequesterModule::class])
class FeaturesModule