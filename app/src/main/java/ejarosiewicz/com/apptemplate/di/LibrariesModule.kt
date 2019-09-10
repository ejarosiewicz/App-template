package ejarosiewicz.com.apptemplate.di

import dagger.Module
import ejarosiewicz.com.android.di.AndroidElementsModule
import ejarosiewicz.com.async.di.AsyncModule
import ejarosiewicz.com.network.di.NetworkModule

@Module(
    includes = [
        AndroidElementsModule::class,
        AsyncModule::class,
        NetworkModule::class
    ]
)
class LibrariesModule