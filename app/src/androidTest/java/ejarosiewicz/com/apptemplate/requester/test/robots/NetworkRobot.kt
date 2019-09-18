package ejarosiewicz.com.apptemplate.requester.test.robots

import android.net.wifi.WifiManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.concurrent.TimeUnit


fun network(function: NetworkRobot.() -> Unit) = NetworkRobot().apply { function() }

class NetworkRobot {

    fun changeNetworkState(enabled: Boolean) {
        val wifi = getSystemService(getApplicationContext(), WifiManager::class.java)
        wifi?.isWifiEnabled = enabled
        if (enabled) {
            Thread.sleep(TimeUnit.SECONDS.toMillis(4))
        }
    }

    fun makeResponseSuccess(
        mockWebServer: MockWebServer,
        mockResponseContent: String
    ) {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockResponseContent)
        mockWebServer.enqueue(mockResponse)
    }

    fun makeResponseFail(mockWebServer: MockWebServer) {
        val mockResponse = MockResponse()
            .setResponseCode(400)
        mockWebServer.enqueue(mockResponse)
    }
}