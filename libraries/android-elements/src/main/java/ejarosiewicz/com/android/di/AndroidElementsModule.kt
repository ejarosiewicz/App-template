package ejarosiewicz.com.android.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ejarosiewicz.com.android.connection.AndroidNetworkConnection
import ejarosiewicz.com.android.connection.NetworkConnection
import ejarosiewicz.com.android.imageloader.GlideImageLoader
import ejarosiewicz.com.android.imageloader.ImageLoader
import ejarosiewicz.com.android.logger.AndroidLogger
import ejarosiewicz.com.android.logger.Logger
import ejarosiewicz.com.android.viewmodelfactory.GlobalViewModelFactory
import javax.inject.Singleton

@Module
abstract class AndroidElementsModule {

    @Binds
    @Singleton
    abstract fun bindsViewModelFactory(globalViewModelFactory: GlobalViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    abstract fun bindsLogger(logger: AndroidLogger): Logger

    @Binds
    @Singleton
    abstract fun bindsNetworkConnection(networkConnection: AndroidNetworkConnection): NetworkConnection

    @Binds
    @Singleton
    abstract fun bindsImageLoader(imageLoader: GlideImageLoader): ImageLoader
}