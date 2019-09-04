package ejarosiewicz.com.android.logger

import android.util.Log

class AndroidLogger : Logger {

    override fun logError(tag: String, log: String) {
        Log.e(tag, log)
    }
}