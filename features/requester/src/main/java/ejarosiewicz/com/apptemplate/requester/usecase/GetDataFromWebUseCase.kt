package ejarosiewicz.com.apptemplate.requester.usecase

import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow
import io.reactivex.Single

interface GetDataFromWebUseCase {

    fun load(): Single<DataToShow>
}