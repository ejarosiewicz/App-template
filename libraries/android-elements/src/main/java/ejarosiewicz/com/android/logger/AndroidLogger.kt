package ejarosiewicz.com.android.logger

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidLogger @Inject constructor(): Logger {

    override fun logError(tag: String, log: String) {
        Log.e(tag, log)
    }
}