package ejarosiewicz.com.apptemplate.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ejarosiewicz.com.apptemplate.data.room.dao.TextDataDAO
import ejarosiewicz.com.apptemplate.data.room.pojo.AdditionalGibberish
import ejarosiewicz.com.apptemplate.data.room.pojo.TextData

@Database(
    entities = [TextData::class,
        AdditionalGibberish::class],
    version = 1
)
abstract class TextDataDatabase : RoomDatabase() {

    abstract fun textDatatDao(): TextDataDAO
}