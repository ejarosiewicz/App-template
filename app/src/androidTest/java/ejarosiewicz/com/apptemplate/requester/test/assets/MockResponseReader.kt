package ejarosiewicz.com.apptemplate.requester.test.assets

import java.io.IOException
import java.io.InputStream

object MockResponseReader {

    fun read(fileName: String): String {
        try {
            val inputStream = javaClass.classLoader
                ?.getResourceAsStream(getResponsePath(fileName))
            return if (inputStream != null) {
                inputStreamToString(inputStream)
            } else {
                "No file found"
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun getResponsePath(fileName: String)=
        "assets/responses/somerequest/$fileName"

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