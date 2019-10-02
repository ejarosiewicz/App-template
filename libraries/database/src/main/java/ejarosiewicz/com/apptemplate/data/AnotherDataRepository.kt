package ejarosiewicz.com.apptemplate.data

import ejarosiewicz.com.apptemplate.data.room.pojo.TextData

interface AnotherDataRepository {

    fun create(textData: TextData)

    fun read()

    fun delete(textDataId: Long)
}