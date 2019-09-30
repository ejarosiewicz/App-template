package ejarosiewicz.com.apptemplate.requester.test.robots

import android.net.wifi.WifiManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import java.util.concurrent.TimeUnit

fun network(function: NetworkRobot.() -> Unit) = NetworkRobot().apply { function() }

class NetworkRobot {

    fun changeNetworkState(enabled: Boolean) {
        val wifi = getSystemService(getApplicationContext(), WifiManager::class.java)
        wifi?.isWifiEnabled = enabled
        Thread.sleep(TimeUnit.SECONDS.toMillis(5))
    }
}
