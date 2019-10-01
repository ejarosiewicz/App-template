package ejarosiewicz.com.apptemplate.databaser.data

import ejarosiewicz.com.apptemplate.databaser.usecase.data.DataToShow

sealed class DatabaserState
class RequestSuccessful(val data: List<DataToShow>) : DatabaserState()
object RequestLoading : DatabaserState()
object RequestNoNetwork : DatabaserState()
object RequestFailed : DatabaserState()