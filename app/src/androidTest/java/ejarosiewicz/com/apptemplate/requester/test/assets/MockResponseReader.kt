package ejarosiewicz.com.apptemplate.requester.test.assets

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream

object MockResponseReader {

    fun read(assetPath: String): String {
        try {
            val inputStream = javaClass.classLoader
                ?.getResourceAsStream("assets/responses/somerequest/$assetPath")
            return if (inputStream != null) {
                inputStreamToString(inputStream)
            } else {
                "No file found"
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun inputStreamToString(inputStream: InputStream): String {
        val stringBuilder = StringBuilder()
        val bufferedReader = inputStream.bufferedReader()
        bufferedReader.lines().forEach {
            stringBuilder.append(it)
        }
        bufferedReader.close()
        return stringBuilder.toString()
    }
}