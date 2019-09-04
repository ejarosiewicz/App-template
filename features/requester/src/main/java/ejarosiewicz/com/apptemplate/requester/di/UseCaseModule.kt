package ejarosiewicz.com.apptemplate.requester.di

import dagger.Binds
import dagger.Module
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCaseImpl
import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverterImpl
import javax.inject.Singleton


@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindsGetDataFromWebUseCase(getDataFromWebUseCase: GetDataFromWebUseCaseImpl): GetDataFromWebUseCase

    @Binds
    @Singleton
    abstract fun bindsSomeDataToDataToShowConverter(someDataToDataToShowConverter: SomeDataToDataToShowConverterImpl)
            : SomeDataToDataToShowConverter
}