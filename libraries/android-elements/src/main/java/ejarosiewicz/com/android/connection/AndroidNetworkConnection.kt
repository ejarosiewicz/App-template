package ejarosiewicz.com.android.connection

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidNetworkConnection @Inject constructor(private val context: Context): NetworkConnection {

    override fun isEnabled(): Boolean {
        val connectivityManager = getSystemService(context, ConnectivityManager::class.java)
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo?.isConnected == true
    }
}