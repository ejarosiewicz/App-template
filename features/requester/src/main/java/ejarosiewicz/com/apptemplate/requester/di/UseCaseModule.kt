package ejarosiewicz.com.apptemplate.requester.di

import dagger.Binds
import dagger.Module
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCaseImpl
import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.requester.usecase.converter.SomeDataToDataToShowConverterImpl

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindsGetDataFromWebUseCase(getDataFromWebUseCase: GetDataFromWebUseCaseImpl): GetDataFromWebUseCase

    @Binds
    abstract fun bindsSomeDataToDataToShowConverter(someDataToDataToShowConverter: SomeDataToDataToShowConverterImpl)
            : SomeDataToDataToShowConverter
}