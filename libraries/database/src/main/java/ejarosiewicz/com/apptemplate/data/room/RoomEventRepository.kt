package ejarosiewicz.com.apptemplate.data.room


import ejarosiewicz.com.apptemplate.data.AnotherDataRepository
import ejarosiewicz.com.apptemplate.data.room.pojo.TextData
import ejarosiewicz.com.apptemplate.data.room.dao.TextDataDAO

class RoomEventRepository(private val dao: TextDataDAO) : AnotherDataRepository {

    override fun create(textData: TextData) {
       dao.create(textData)
    }

    override fun read() {
        dao.read()
    }

    override fun delete(textDataId: Long) {
        dao.delete(textDataId)
    }

}