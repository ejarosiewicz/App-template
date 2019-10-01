package ejarosiewicz.com.apptemplate.databaser.usecase

import ejarosiewicz.com.apptemplate.databaser.usecase.data.DataToShow
import io.reactivex.Single

interface GetDataFromWebUseCase {

    fun load(): Single<List<DataToShow>>
}