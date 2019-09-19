package ejarosiewicz.com.apptemplate.requester.test.assets

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


object FailDispatcher: Dispatcher() {
    override fun dispatch(request: RecordedRequest?): MockResponse =
        MockResponse().setResponseCode(400)
}

class SuccessfulDispatcher(private val responseBody: String): Dispatcher(){
    override fun dispatch(request: RecordedRequest?): MockResponse =
        MockResponse().setResponseCode(200).setBody(responseBody)
}