package ejarosiewicz.com.apptemplate.requester.data

import ejarosiewicz.com.apptemplate.requester.usecase.data.DataToShow

sealed class RequesterState
class RequestSuccessful(val data: List<DataToShow>) : RequesterState()
object RequestNoNetwork : RequesterState()
object RequestFailed : RequesterState()