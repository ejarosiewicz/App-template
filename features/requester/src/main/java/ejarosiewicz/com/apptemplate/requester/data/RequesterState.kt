package ejarosiewicz.com.apptemplate.requester.data

sealed class RequesterState
class RequestSuccessful(val data: String) : RequesterState()
object RequestNoNetwork : RequesterState()
object RequestFailed : RequesterState()