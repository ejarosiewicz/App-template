package ejarosiewicz.com.apptemplate.databaser.di

import dagger.Binds
import dagger.Module
import ejarosiewicz.com.apptemplate.databaser.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.databaser.usecase.GetDataFromWebUseCaseImpl
import ejarosiewicz.com.apptemplate.databaser.usecase.converter.AnotherDataToDataToShowConverter
import ejarosiewicz.com.apptemplate.databaser.usecase.converter.AnotherDataToDataToShowConverterImpl
import javax.inject.Singleton


@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindsGetDataFromWebUseCase(getDataFromWebUseCase: GetDataFromWebUseCaseImpl): GetDataFromWebUseCase

    @Binds
    @Singleton
    abstract fun bindsAnotherDataToDataToShowConverter(someDataToDataToShowConverter: AnotherDataToDataToShowConverterImpl)
            : AnotherDataToDataToShowConverter
}