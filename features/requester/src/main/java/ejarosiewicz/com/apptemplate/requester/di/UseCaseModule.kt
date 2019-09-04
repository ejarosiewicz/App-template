package ejarosiewicz.com.apptemplate.requester.di

import dagger.Binds
import dagger.Module
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCase
import ejarosiewicz.com.apptemplate.requester.usecase.GetDataFromWebUseCaseImpl

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindsGetDataFromWebUseCase(getDataFromWebUseCase: GetDataFromWebUseCaseImpl): GetDataFromWebUseCase
}